package com.exercicio.algaworks.algafoodapi.service;

import com.exercicio.algaworks.algafoodapi.domain.model.Cozinha;
import com.exercicio.algaworks.algafoodapi.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha save(@RequestBody Cozinha cozinha) { return cozinhaRepository.save(cozinha); }

    public void deleteCozinha(Cozinha cozinha){

        cozinhaRepository.delete(cozinha);
    }

    public Optional<Cozinha> consunt(Long id){
       return cozinhaRepository.findById(id);
    }


}
