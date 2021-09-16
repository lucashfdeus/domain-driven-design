package br.edu.fasam.clean.service;

import br.edu.fasam.clean.exception.BusinessEntityNotFoundException;
import br.edu.fasam.clean.interfaces.IService;
import br.edu.fasam.clean.model.Address;
import br.edu.fasam.clean.repository.AddressRepository;
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
public class AddressService implements IService<Address, Long> {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address create(Address address) {
        log.trace("Criando address: {}", address);
        return addressRepository.save(address);
    }

    @Override
    public Address read(Long id) {
        log.trace("Consultando registro com o identificador %d {}", id);
        return addressRepository.findById(id).orElseThrow(() ->
                new BusinessEntityNotFoundException(
                        String.format("Não foi possível encontrar a apontamento com identifacador %d.", id)));
    }

    @Override
    public Page<Address> read(Address address, Pageable pageable) {
        log.trace("Criando matcher para fazera  consulta baseado nos dados do objeto passado como argumento: {}", address);
        log.trace("Dados da paginação: {}", pageable);

        var caseInsensitiveMatcher = ExampleMatcher.matchingAll()
                .withMatcher("street", match -> match.startsWith())
                .withIgnorePaths(new String[]{"suite", "city", "zipcode", "geo" })
                .withIgnoreCase();

        var filtro = Example.of(address, caseInsensitiveMatcher);

        log.trace("Filtro de consulta: {}", filtro);
        log.trace("Executando método de read com os argumentos informados.");

        return addressRepository.findAll(filtro, pageable);
    }

    @Override
    public void update(Address address) {
        log.trace("Verificando se registro de address está nulo.");
        Optional<Address> value = Optional.ofNullable(address);
        log.trace("Verificando se o código do address foi informado para atualização.");
        if(value.isPresent() && value.get().getId() != null){
            addressRepository.findById(address.getId()).ifPresentOrElse(
                    (addressFound) -> {
                        log.trace("Atualizando registro de address. {}", address);
                        addressRepository.save(address);
                    },
                    () -> {
                        log.trace("Registro não encontrado. Identificador %d.", address.getId());
                        new BusinessEntityNotFoundException(String.format("Não foi possível encontrar a apontamento com identifacador %d.", address.getId()));
                    }
            );
        }
    }

    @Override
    public void delete(Long id) {
        addressRepository.findById(id).ifPresentOrElse(
                (addressFound) -> {
                    log.trace("Atualizando registro de address. {}", id);
                    addressRepository.delete(addressFound);
                },
                () -> {
                    log.trace("Registro não encontrado. Identificador %d.", id);
                    new BusinessEntityNotFoundException(String.format("Não foi possível encontrar o address com identifacador %d.", id));
                }
        );
    }
}
