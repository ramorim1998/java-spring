package com.rafael.crudspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.repository.CursosRepository;

import lombok.AllArgsConstructor;

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
    public Cursos create(@RequestBody Cursos curso) {
        return cursosRepository.save(curso);
        //return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

}
