package com.rafael.crudspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.crudspring.dto.CursoDTO;
import com.rafael.crudspring.enums.Categoria;
import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.dto.LicaoDTO;


@Component
public class CursoMapper {
    
    public CursoDTO toDTO(Cursos cursos){
        if(cursos == null){
            return null;
        }
        List<LicaoDTO> licao = cursos.getLicoes().stream()
        .map(licao$ -> new LicaoDTO(licao$.getId(), licao$.getName(), licao$.getYoutubeString()))
        .collect(Collectors.toList());
        return new CursoDTO(cursos.getId(), cursos.getName(), cursos.getCategory().getValue(), licao);
    }

    public Cursos toEntity(CursoDTO cursoDTO){
        if(cursoDTO == null){
            return null;
        }

        Cursos cursos = new Cursos();

        if(cursoDTO.id()!= null){
            cursos.setId(cursoDTO.id());
        }
        cursos.setName(cursoDTO.name());
        cursos.setCategory(convertCategoria(cursoDTO.category()));
        return cursos;
    }

    public Categoria convertCategoria(String value){
        if(value == null){
            return null;
        }
        return switch (value){
            case "Back-end"-> Categoria.BACK_END;
            case "Front-end" -> Categoria.FRONT_END;
            default -> throw new IllegalArgumentException("Categoria invalida:" + value);
        };

    }
}
