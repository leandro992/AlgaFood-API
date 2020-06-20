package com.exercicio.algaworks.algafoodapi.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;


}
