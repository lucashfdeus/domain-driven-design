package br.edu.fasam.clean.resource;

import br.edu.fasam.clean.contract.company.CompanyRequest;
import br.edu.fasam.clean.contract.company.CompanyResponse;
import br.edu.fasam.clean.interfaces.IController;
import br.edu.fasam.clean.util.GenericDefaultController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Operações relativas ao resource Address", tags = "address")
@RequestMapping(value = "/company", path = "/company")
public class CompanyResource extends GenericDefaultController
        implements IController<CompanyResponse, CompanyRequest, Long> {

    @Override
    public ResponseEntity<CompanyResponse> create(CompanyRequest entity) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyResponse> read(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Page<CompanyResponse>> read(CompanyRequest entity, Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyResponse> update(Long id, CompanyRequest entity) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyResponse> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyResponse> options() {
        return null;
    }
}
