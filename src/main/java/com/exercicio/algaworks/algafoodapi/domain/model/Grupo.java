package com.exercicio.algaworks.algafoodapi.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Grupo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToMany
    @JoinTable(name = "permissao_grupo",
            joinColumns = @JoinColumn(name = "grupo_id"),
    inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissoes;


    public Grupo(){
        permissoes = new ArrayList<>();
    }

}
