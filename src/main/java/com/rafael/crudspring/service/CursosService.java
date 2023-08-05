package com.rafael.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.repository.CursosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CursosService {
    private CursosRepository cursosRepository;

    public CursosService(CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }

    public List<Cursos> list() {
        return cursosRepository.findAll();
    }

    public Optional<Cursos> findById(@NotNull @Positive Long id) {
        return cursosRepository.findById(id);
    }

    public Cursos create(@Valid Cursos curso) {
        return cursosRepository.save(curso);
        // return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    public Optional<Cursos> update( @NotNull @Positive Long id, @Valid Cursos curso){
        return cursosRepository.findById(id)
        .map(record -> {
            record.setName(curso.getName());
            record.setCategory(curso.getCategory());
           return cursosRepository.save(record);
        });
    }

    public boolean delete(@NotNull @Positive Long id){
        return cursosRepository.findById(id)
        .map(record -> {
            cursosRepository.deleteById(id);
            return true;
        })
        .orElse(false);    
    }
}
