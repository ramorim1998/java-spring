package com.rafael.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.rafael.crudspring.enums.Categoria;
import com.rafael.crudspring.enums.Status;
import com.rafael.crudspring.enums.conversores.CategoriaConverter;
import com.rafael.crudspring.enums.conversores.StatusConverter;

@Data //getters setters constructor
@Entity //vai ser uma tabela no banco de dados
@SQLDelete(sql = "UPDATE Cursos SET status = 'inactive' WHERE id = ?") //o metodo delete não muda mas a logica do q vai acontecer no sql sim
@Where(clause = "status = 'active'")
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
    @Convert(converter = CategoriaConverter.class)
    @Column( length = 10, nullable = false) // setar o length é bom pra evitar que o banco fique grande demais
    private Categoria category;

    @NotNull
    @Convert(converter = StatusConverter.class)
    @Column(length = 10, nullable = false) 
    private Status status = Status.ATIVO;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "curso")
    //@JoinColumn(name = "curso_id")
    private List<Licao> licoes = new ArrayList<>();

}
