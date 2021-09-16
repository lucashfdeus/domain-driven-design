package br.edu.fasam.clean.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

/**
 * Interface genérica para camada de controle
 *
 * @param <E> Parâmetro relativo ao contrato de Request
 * @param <T> Parâmetro relativo ao contrato de Response
 * @param <N> Parâmetro relativo ao tipo do Identificador da classe de modelo
 */
public interface IController<E, T, N> {

    /**
     * @param entity
     * @return
     */
    ResponseEntity<E> create(T entity);

    /**
     *
     * @param id
     * @return
     */
    ResponseEntity<E> read(N id);

    /**
     *
     * @param entity
     * @param page
     * @param size
     * @return
     */
    ResponseEntity<Page<E>> read(T entity, Integer page, Integer size);

    /**
     *
     * @param id
     * @param entity
     * @return
     */
    ResponseEntity<E> update(N id, T entity);

    /**
     *
     * @param id
     * @return
     */
    ResponseEntity<E> delete(N id);

    /**
     *
     * @return
     */
    ResponseEntity<E> options();

}
