package com.exercicio.algaworks.algafoodapi.controller;

import com.exercicio.algaworks.algafoodapi.domain.model.Cozinha;
import com.exercicio.algaworks.algafoodapi.repository.CozinhaRepository;
import com.exercicio.algaworks.algafoodapi.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> list() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Cozinha> consult(@PathVariable Long code) {
        Optional<Cozinha> consutCozinha = cozinhaService.consunt(code);

        if (consutCozinha.isPresent()) return ResponseEntity.ok(consutCozinha.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Cozinha cozinha) {
        cozinhaService.save(cozinha);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Cozinha> update(@PathVariable Long code, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> cozinhaAtual = cozinhaService.consunt(code);
        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            cozinhaService.save(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaAtual.get());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Cozinha> delete(@PathVariable Long code) {
        Optional<Cozinha> byId = cozinhaRepository.findById(code);
        try {
            if (!byId.isEmpty()) {
                cozinhaService.deleteCozinha(byId.get());
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}