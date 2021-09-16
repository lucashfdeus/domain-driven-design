package br.edu.fasam.clean.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface genérica para camada de controle
 *
 * @param <T> Parâmetro relativo ao modelo de domínio
 * @param <N> Parâmetro relativo ao tipo do Identificador da classe de modelo
 */
public interface IService<T, N> {

    T create(T entity);

    T read(N id);

    Page<T> read(T entity, Pageable pageable);

    void update(T entity);

    void delete(N id);

}
