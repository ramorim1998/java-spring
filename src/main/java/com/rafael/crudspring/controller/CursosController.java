package com.rafael.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crudspring.dto.CursoDTO;
import com.rafael.crudspring.service.CursosService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    private final CursosService cursosService;

    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    @GetMapping
    public List<CursoDTO> list() {
        return cursosService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody @Valid @NotNull CursoDTO curso) {
        return cursosService.create(curso);
    }

    @GetMapping("/{id}") 
    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursosService.findById(id);
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CursoDTO curso) {
        return cursosService.update(id, curso);
                
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
       cursosService.delete(id);
    }

}
