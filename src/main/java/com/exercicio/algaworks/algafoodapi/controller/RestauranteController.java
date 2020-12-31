package com.exercicio.algaworks.algafoodapi.controller;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import com.exercicio.algaworks.algafoodapi.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> list(){
        return ResponseEntity.ok(restauranteService.list());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurante> consult(@PathVariable Long id){
       return ResponseEntity.ok(restauranteService.consult(id));
    }

    @PostMapping
    public ResponseEntity<Restaurante> save(@RequestBody Restaurante request){
        Restaurante response = restauranteService.save(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante request){
        Optional<Restaurante> restaurante = Optional.ofNullable(restauranteService.consult(id));
        if(restaurante.isPresent()) {
            BeanUtils.copyProperties(request, restaurante.get(), "id", "formaPagamentos",
                    "endereco","dataCadastro", "produtos");
            return ResponseEntity.ok(restauranteService.save(restaurante.get()));
        }
        return  ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateParcial(@PathVariable Long id, @RequestBody Map<String,Object> camposOrigem){
        Restaurante restaurante = restauranteService.consult(id);
        if(restaurante == null){
            return ResponseEntity.notFound().build();
        }
        merge(camposOrigem, restaurante);
        return update(id, restaurante);
    }


    public void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino){
        ObjectMapper objectMapper = new ObjectMapper();

        Restaurante restaurante = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) ->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(Boolean.TRUE);

            Object novoValor = ReflectionUtils.getField(field, restaurante);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }





}
