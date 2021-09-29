package br.edu.fasam.clean.service;

import br.edu.fasam.clean.exception.BusinessEntityNotFoundException;
import br.edu.fasam.clean.interfaces.IService;
import br.edu.fasam.clean.model.Address;
import br.edu.fasam.clean.model.Geo;
import br.edu.fasam.clean.repository.GeoRepository;
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
public class GeoService implements IService<Geo, Long> {


    @Autowired
    GeoRepository geoRepository;

    @Override
    public Geo create(Geo geo) {
        log.trace("Criando geo: {}", geo);
        return geoRepository.save(geo);
    }

    @Override
    public Geo read(Long id) {
        log.trace("Consultando registro com o identificador %d {}", id);
        return geoRepository.findById(id).orElseThrow(() ->
                new BusinessEntityNotFoundException(
                        String.format("Não foi possível encontrar a apontamento com identifacador %d.", id)));
    }

    @Override
    public Page<Geo> read(Geo geo, Pageable pageable) {
        log.trace("Criando matcher para fazer a consulta baseado nos dados do objeto passado como argumento: {}", geo);
        log.trace("Dados da paginação: {}", pageable);

        //Tirar dúvida referente ao método de filtrar e matcher
        var caseInsensitiveMatcher = ExampleMatcher.matchingAll()
                .withMatcher("lat", match -> match.startsWith())
                .withIgnorePaths("lat","lng")
                .withIgnoreCase();
        var filtro = Example.of(geo, caseInsensitiveMatcher);

        log.trace("Filtro de consulta: {}", filtro);
        log.trace("Executando método de read com os argumentos informados.");

        return geoRepository.findAll(filtro, pageable);
    }

    @Override
    public void update(Geo geo) {
        log.trace("Verificando se registro de geo está nulo.");
        Optional<Geo> value = Optional.ofNullable(geo);
        log.trace("Verificando se o código do geo foi informado para atualização.");
        if(value.isPresent() && value.get().getId() != null){
            geoRepository.findById(geo.getId()).ifPresentOrElse(
                    (geoFound) -> { //o que seria GeoFound?
                        log.trace("Atualizando registro de Geo. {}", geo);
                        geoRepository.save(geo);
                    },
                    () -> {
                        log.trace("Registro não encontrado. Identificador %d.", geo.getId());
                        new BusinessEntityNotFoundException(String.format("Não foi possível encontrar a apontamento com identifacador %d.", geo.getId()));
                    }
            );
        }
    }

    @Override
    public void delete(Long id) {
        geoRepository.findById(id).ifPresentOrElse(
                (geoFound) -> {
                    log.trace("Atualizando registro de address. {}", id);
                    geoRepository.delete(geoFound);
                },
                () -> {
                    log.trace("Registro não encontrado. Identificador %d.", id);
                    new BusinessEntityNotFoundException(String.format("Não foi possível encontrar o address com identifacador %d.", id));
                }
        );
    }
}
