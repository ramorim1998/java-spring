package com.rafael.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Licao {

    @Id //indicar que é chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO) //gera o valor automaticamente pelo banco de dados
    private Long id;

    @Column(length=100, nullable = false)
    private String name;

    @Column(length = 11, nullable = false)
    private String youtubeString;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cursos curso;
    
}
