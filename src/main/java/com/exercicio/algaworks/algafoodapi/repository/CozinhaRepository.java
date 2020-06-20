package com.exercicio.algaworks.algafoodapi.repository;

import com.exercicio.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CozinhaRepository extends JpaRepository<Cozinha,Long>  {


}
