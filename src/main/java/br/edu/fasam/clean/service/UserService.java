package br.edu.fasam.clean.service;

import br.edu.fasam.clean.exception.BusinessEntityNotFoundException;
import br.edu.fasam.clean.interfaces.IService;
import br.edu.fasam.clean.model.Address;
import br.edu.fasam.clean.model.Company;
import br.edu.fasam.clean.model.User;
import br.edu.fasam.clean.repository.UserRepository;
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
public class UserService implements IService<User, Long> {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        log.trace("Criando user: {}", user);
        return userRepository.save(user);
    }
    @Override
    public User read(Long id) {
        log.trace("Consultando registro com o identificador %d {}", id);
        return userRepository.findById(id).orElseThrow(() ->
                new BusinessEntityNotFoundException(
                        String.format("Não foi possível encontrar a apontamento com identifacador %d.", id)));
    }

    @Override
    public Page<User> read(User user, Pageable pageable) {
        log.trace("Criando matcher para fazera  consulta baseado nos dados do objeto passado como argumento: {}", user);
        log.trace("Dados da paginação: {}", pageable);

        //Tirar dúvida referente ao método de filtrar e matcher
        var caseInsensitiveMatcher = ExampleMatcher.matchingAll()
                .withMatcher("name", match -> match.startsWith())
                .withIgnorePaths(new String[]{"username", "email", "address", "phone", "website", "company" })
                .withIgnoreCase();

        var filtro = Example.of(user, caseInsensitiveMatcher);

        log.trace("Filtro de consulta: {}", filtro);
        log.trace("Executando método de read com os argumentos informados.");

        return userRepository.findAll(filtro, pageable);
    }
    @Override
    public void update(User user) {
        log.trace("Verificando se registro de user está nulo.");
        Optional<User> value = Optional.ofNullable(user);
        log.trace("Verificando se o código do User foi informado para atualização.");
        if(value.isPresent() && value.get().getId() != null){
            userRepository.findById(user.getId()).ifPresentOrElse(
                    (addressFound) -> {
                        log.trace("Atualizando registro de address. {}", user);
                        userRepository.save(user);
                    },
                    () -> {
                        log.trace("Registro não encontrado. Identificador %d.", user.getId());
                        new BusinessEntityNotFoundException(String.format("Não foi possível encontrar a apontamento com identifacador %d.", user.getId()));
                    }
            );
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresentOrElse(
                (userFound) -> {
                    log.trace("Atualizando registro de address. {}", id);
                    userRepository.delete(userFound);
                },
                () -> {
                    log.trace("Registro não encontrado. Identificador %d.", id);
                    new BusinessEntityNotFoundException(String.format("Não foi possível encontrar o address com identifacador %d.", id));
                }
        );
    }
}
