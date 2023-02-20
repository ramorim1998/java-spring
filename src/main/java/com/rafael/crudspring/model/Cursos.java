package com.rafael.crudspring.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data //getters setters constructor
@Entity //vai ser uma tabela no banco de dados
//@Table("cursos") = > codigo legado ou se ja tiver uma tabela mas ainda não classe
public class Cursos {

    @Id //indicar que é chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO) //gera o valor automaticamente pelo banco de dados
    @JsonProperty("_id")
    private Long id;
 
    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false) //pra cada outra coluna que não seja ID mas ja fica implicito caso vc ja tenha declarado id
    private String name;

    @NotNull
    @Length(max=10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 20, nullable = false) // setar o length é bom pra evitar que o banco fique grande demais
    private String category;

}
