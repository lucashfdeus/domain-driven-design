package br.edu.fasam.clean.service;

import br.edu.fasam.clean.exception.BusinessEntityNotFoundException;
import br.edu.fasam.clean.interfaces.IService;
import br.edu.fasam.clean.model.Address;
import br.edu.fasam.clean.model.Company;
import br.edu.fasam.clean.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CompanyService implements IService<Company, Long>{

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        log.trace("Criando company: {}", company);
        return companyRepository.save(company);
    }

    @Override
    public Company read(Long id) {
        log.trace("Consultando registro com o identificador %d {}", id);
        return companyRepository.findById(id).orElseThrow(() ->
                new BusinessEntityNotFoundException(
                        String.format("Não foi possível encontrar a apontamento com identifacador %d.", id)));
    }

    @Override
    public Page<Company> read(Company company, Pageable pageable) {

        log.trace("Criando matcher para fazer a consulta baseado nos dados do objeto passado como argumento: {}", company);
        log.trace("Dados da paginação: {}", pageable);

        //Tirar dúvida referente ao método de filtrar e matcher
        var caseInsensitiveMatcher = ExampleMatcher.matchingAll()
                .withMatcher("lat", match -> match.startsWith())
                .withMatcher("lng", match -> match.startsWith())
                .withIgnoreCase();
        var filtro = Example.of(company, caseInsensitiveMatcher);
        log.trace("Filtro de consulta: {}", filtro);
        log.trace("Executando método de read com os argumentos informados.");

        return companyRepository.findAll(filtro, pageable);
    }

    @Override
    public void update(Company company) {

        log.trace("Verificando se registro de company está nulo.");
        Optional<Company> value = Optional.ofNullable(company);
        log.trace("Verificando se o código do address foi informado para atualização.");
        if (value.isPresent() && value.get().getId() != null) {
            companyRepository.findById(company.getId()).ifPresentOrElse(
                    (addressFound) -> {
                        log.trace("Atualizando registro de address. {}", company);
                        companyRepository.save(company);
                    },
                    () -> {
                        log.trace("Registro não encontrado. Identificador %d.", company.getId());
                        new BusinessEntityNotFoundException(String.format("Não foi possível encontrar a apontamento com identifacador %d.", company.getId()));
                    }
            );
        }
    }

    @Override
    public void delete(Long id) {
        companyRepository.findById(id).ifPresentOrElse(
                (companyFound) -> {
                    log.trace("Atualizando registro de company. {}", id);
                    companyRepository.delete(companyFound);
                },
                () -> {
                    log.trace("Registro não encontrado. Identificador %d.", id);
                    new BusinessEntityNotFoundException(String.format("Não foi possível encontrar o company com identifacador %d.", id));
                }
        );
    }
}