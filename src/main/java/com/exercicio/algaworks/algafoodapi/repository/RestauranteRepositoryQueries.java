package com.exercicio.algaworks.algafoodapi.repository;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;


public interface RestauranteRepositoryQueries {

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> findCriteria();

    List<Restaurante> findComFreteGratis(String nome);

}
