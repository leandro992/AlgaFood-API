package com.exercicio.algaworks.algafoodapi.repository;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {




}
