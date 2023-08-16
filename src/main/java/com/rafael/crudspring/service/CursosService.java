package com.rafael.crudspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rafael.crudspring.dto.CursoDTO;
import com.rafael.crudspring.exception.RecordNotFoundException;
import com.rafael.crudspring.mapper.CursoMapper;
import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.repository.CursosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CursosService {
    private CursosRepository cursosRepository;
    private CursoMapper cursoMapper;

    public CursosService(CursosRepository cursosRepository, CursoMapper cursoMapper) {
        this.cursosRepository = cursosRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursosRepository.findAll().stream()
        .map(cursoMapper::toDTO)
        .collect(Collectors.toList());
    }

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursosRepository.findById(id).map(cursoMapper::toDTO).
        orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return  cursoMapper.toDTO( cursosRepository.save(cursoMapper.toEntity(curso)));
        // return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    public CursoDTO update( @NotNull @Positive Long id, @Valid @NotNull CursoDTO curso){
        return cursosRepository.findById(id)
        .map(record -> {
            record.setName(curso.name());
            record.setCategory(curso.category());
           return cursosRepository.save(record);
        }).map(cursoMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id){
        cursosRepository.delete(cursosRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
