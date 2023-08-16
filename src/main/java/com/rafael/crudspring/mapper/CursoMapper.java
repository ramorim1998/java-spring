package com.rafael.crudspring.mapper;

import org.springframework.stereotype.Component;

import com.rafael.crudspring.dto.CursoDTO;
import com.rafael.crudspring.model.Cursos;

@Component
public class CursoMapper {
    
    public CursoDTO toDTO(Cursos cursos){
        if(cursos == null){
            return null;
        }
        return new CursoDTO(cursos.getId(), cursos.getName(), cursos.getCategory());
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
        cursos.setCategory(cursoDTO.category());
        return cursos;
    }
}
