package com.exercicio.algaworks.algafoodapi.domain.model;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

}
