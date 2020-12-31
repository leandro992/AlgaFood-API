package com.exercicio.algaworks.algafoodapi.repository;

import com.exercicio.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CozinhaRepository extends JpaRepository<Cozinha,Long>  {

    List<Cozinha> findByNomeContaining(String nome);

    Boolean existsByNome(String nome);

    /**
     *
     * @param nome
     * @return lista com o prefixo read
     */
    List<Cozinha> readByNomeContaining(String nome);

    /**
     *
     * @param nome
     * @return lista com o prefixo get
     */
    List<Cozinha> getByNomeContaining(String nome);

    /**
     *
     * @param nome
     * @return lista com o prefixo stremio
     */
    List<Cozinha> streamByNomeContaining(String nome);
}
