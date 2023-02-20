package com.rafael.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
import com.rafael.crudspring.repository.CursosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Validated
@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursosController {

    private CursosRepository cursosRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Cursos> list() {
        return cursosRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cursos create(@RequestBody @Valid Cursos curso) {
        return cursosRepository.save(curso);
        //return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @GetMapping("/{id}") //(id) é o valor que a gente vai receber do params do front
    public ResponseEntity <Cursos> findById(@PathVariable @NotNull @Positive Long id){
        return cursosRepository.findById(id)
        .map(curso -> ResponseEntity.ok().body(curso))
        .orElse(ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}")
    public ResponseEntity <Cursos> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Cursos curso){
        return cursosRepository.findById(id)
        .map(record -> {
            record.setName(curso.getName());
            record.setCategory(curso.getCategory());
            Cursos updated  = cursosRepository.save(record);
            return ResponseEntity.ok().body(updated);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        return cursosRepository.findById(id)
        .map(record -> {
            cursosRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());    
    }

}
