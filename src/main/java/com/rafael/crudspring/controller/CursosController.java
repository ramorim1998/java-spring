package com.rafael.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.rafael.crudspring.model.Cursos;
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
    public List<Cursos> list() {
        return cursosService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cursos create(@RequestBody @Valid Cursos curso) {
        return cursosService.create(curso);
        // return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("/{id}") // (id) é o valor que a gente vai receber do params do front
    public ResponseEntity<Cursos> findById(@PathVariable @NotNull @Positive Long id) {
        return cursosService.findById(id)
                .map(curso -> ResponseEntity.ok().body(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Cursos curso) {
        return cursosService.update(id, curso)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        if (cursosService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }

        return ResponseEntity.notFound().build();
    }

}
