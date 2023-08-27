package com.rafael.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//record é uma class java q n tem constructor vazio e sem setters -> so seta pelo constructor tornando ela imutavel

public record CursoDTO(    @JsonProperty("_id") Long id,
 @NotBlank @NotNull @Length(min = 5, max = 100) String name, 
 @NotNull @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category,
 List<LicaoDTO> licoes) {
    
}
