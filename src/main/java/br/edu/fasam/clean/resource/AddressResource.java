package br.edu.fasam.clean.resource;

import br.edu.fasam.clean.contract.address.AddressRequest;
import br.edu.fasam.clean.contract.address.AddressResponse;
import br.edu.fasam.clean.interfaces.IController;
import br.edu.fasam.clean.mappers.AddressMapper;
import br.edu.fasam.clean.model.Address;
import br.edu.fasam.clean.service.AddressService;
import br.edu.fasam.clean.util.GenericDefaultController;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Operações relativas ao resource Address", tags = "address")
@RequestMapping(value = "/address", path = "/address")
public class AddressResource extends GenericDefaultController
        implements IController<AddressResponse, AddressRequest, Long> {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public ResponseEntity<AddressResponse> create(AddressRequest addressRequest) {
        log.trace("Criando address: {}", addressRequest);

        var objetoConvertido = addressMapper.toModel(addressRequest);
        Address address = addressService.create(objetoConvertido);

        var objetoResposta = addressMapper.toResponse(address);

        HttpHeaders responseHeaders = getHttpHeaders(address);

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(objetoResposta);
    }

    @Override
    public ResponseEntity<AddressResponse> read(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<AddressResponse>> read(AddressRequest addressRequest, Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<AddressResponse> update(Long id, AddressRequest addressRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AddressResponse> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AddressResponse> options() {
        return null;
    }

    @NotNull
    private HttpHeaders getHttpHeaders(Address address) {
        HttpHeaders responseHeaders = getHttpHeaders(address.getId());
        log.trace("Montando cabeçalho de resposta {}", responseHeaders.toString());
        return responseHeaders;
    }
}
