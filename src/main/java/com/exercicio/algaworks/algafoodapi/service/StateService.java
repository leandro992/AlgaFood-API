package com.exercicio.algaworks.algafoodapi.service;

import com.exercicio.algaworks.algafoodapi.domain.model.Estado;
import com.exercicio.algaworks.algafoodapi.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private EstadoRepository stateRepository;

    public Estado save(Estado estado) {
        return stateRepository.save(estado);
    }

    public Estado update(Long code, Estado estado) {

        Optional<Estado> stateById = stateRepository.findById(code);
        if (!stateById.isPresent()) {
            BeanUtils.copyProperties(estado, stateById.get(), "id");
            Estado save = stateRepository.save(stateById.get());
            return save;
        }
        return null;
    }

    public Optional<Estado> consult(Long code) {
        return stateRepository.findById(code);
    }

    public List<Estado> list() {
        return stateRepository.findAll();
    }

    public boolean delete(Long code) {
        Optional<Estado> consult = consult(code);
        if (consult.isPresent()) {
            stateRepository.delete(consult.get());
            return true;
        }
        return false;
    }

}
