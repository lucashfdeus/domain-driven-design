package br.edu.fasam.clean.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public class GenericDefaultController {

    public static String CONTENT_RANGE_HEADER = "Content-Range";
    public static String CONTENT_LENGTH = "Content-Length";


    /**
     * Monta LOCATION para o recurso
     *
     * @param id Identificação do recurso.
     * @return HttpHeaders
     */
    protected HttpHeaders getHttpHeaders(Long id) {
        URI location;

        if (id == null) {
            location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        } else {
            location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, location.toString());
        return responseHeaders;
    }

    protected String debugController(){
        Class thisClass = new Object(){}.getClass();
        String className = thisClass.getEnclosingClass()!=null ? thisClass.getEnclosingClass().getName(): thisClass.getName();
        String methodName = thisClass.getEnclosingMethod().getName();

        return String.format("Classe: %s | Método: %s", className, methodName);
    }

    protected <T> ResponseEntity<Page<T>> getPagedResponse(Page<T> page, Integer pageNumber) {
        ResponseHeaderPaginable paginable = new ResponseHeaderPaginable(pageNumber, page);

        paginable.invoke();

        HttpStatus status = paginable.getStatus();

        return ResponseEntity.status(status).header(CONTENT_RANGE_HEADER, paginable.responsePageRange()).body(page);
    }

    protected class ResponseHeaderPaginable {
        private Integer page;
        private Page<?> list;
        private Integer total;
        private Integer offset;
        private HttpStatus status;

        public ResponseHeaderPaginable(Integer page, Page<?> list) {
            this.page = page;
            this.list = list;
        }

        public Integer getTotal() {
            return total;
        }

        public Integer getOffset() {
            return offset;
        }

        public HttpStatus getStatus() {
            return status;
        }

        private HttpStatus readStatus(Page<?> list, Integer total, Integer offset) {
            return list.getSize() == 0 ? HttpStatus.NO_CONTENT : ((list.getSize() + offset < total ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK));
        }

        public ResponseHeaderPaginable invoke() {
            total = Math.toIntExact(list.getTotalElements());
            offset = Optional.ofNullable(page).orElse(0);
            status = readStatus(list, total, offset);
            return this;
        }

        public String responsePageRange() {
//            return offset + "-" + offset + list.getSize() + "/" + total;
            return String.format("itens: %d - %d/%d", offset, list.getSize(), total);
//            return String.format("bytes %d-%d/%d", offset, (byte)list.getSize(), total.byteValue());
        }
    }
}
