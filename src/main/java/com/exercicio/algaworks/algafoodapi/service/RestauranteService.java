package com.exercicio.algaworks.algafoodapi.service;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import com.exercicio.algaworks.algafoodapi.repository.CozinhaRepository;
import com.exercicio.algaworks.algafoodapi.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante consult(Long id){
        Optional<Restaurante> listRestaurante = restauranteRepository.findById(id);
        return listRestaurante.get();
    }

    public List<Restaurante> list(){
        return restauranteRepository.findAll();
    }

    public Restaurante save(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
}
