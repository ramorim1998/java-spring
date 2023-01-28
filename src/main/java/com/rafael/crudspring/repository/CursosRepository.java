package com.rafael.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.crudspring.model.Cursos;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long> {
    
}
