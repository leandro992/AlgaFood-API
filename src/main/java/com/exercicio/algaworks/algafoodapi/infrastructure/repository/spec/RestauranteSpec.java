package com.exercicio.algaworks.algafoodapi.infrastructure.repository.spec;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpec {


    public static Specification<Restaurante> comFreteGratis(){
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhande(String nome){
        return (root, query, builder) -> builder.like(root.get("nome"),"%" + nome + "%");
    }


}
