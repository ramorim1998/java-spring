package com.rafael.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.repository.CursosRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}
	@Bean
	CommandLineRunner initDataBase(CursosRepository cursosRepository){
        return args -> {
            cursosRepository.deleteAll();

            Cursos c = new Cursos();
            c.setName("angular spring");
            c.setCategory("front-end");
            cursosRepository.save(c);
        };
    }

}
