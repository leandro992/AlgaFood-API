package com.exercicio.algaworks.algafoodapi.controller;

import com.exercicio.algaworks.algafoodapi.domain.model.Estado;
import com.exercicio.algaworks.algafoodapi.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/state", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<Estado>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.list());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Optional<Estado>> consult(@PathVariable Long code) {
        Optional<Estado> listState = stateService.consult(code);
        if (listState.isPresent()) return ResponseEntity.ok(listState);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Estado estado) { stateService.save(estado); }

    @PutMapping("/{code}")
    public ResponseEntity<Estado> update(@PathVariable Long code, @RequestBody Estado estado) {

       Optional<Estado> stateById = stateService.consult(code);
        if (stateById.isPresent()) {
            BeanUtils.copyProperties(estado, stateById.get(), "id");
            Estado save = stateService.save(stateById.get());
            return ResponseEntity.ok(save);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Estado> delete(@PathVariable Long code){
        boolean delete = stateService.delete(code);
        if (delete) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


}
