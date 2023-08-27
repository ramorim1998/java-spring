package com.rafael.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.crudspring.enums.Categoria;
import com.rafael.crudspring.model.Cursos;
import com.rafael.crudspring.model.Licao;
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
            c.setCategory(Categoria.BACK_END);


            Licao l = new Licao();
            l.setName("intro");
            l.setYoutubeString("www.youtu");
            l.setCurso(c);
            c.getLicoes().add(l);

            Licao l2 = new Licao();
            l2.setName("Angular");
            l2.setYoutubeString("www.youtb");
            l2.setCurso(c);
            c.getLicoes().add(l2);
            cursosRepository.save(c);
        };
    }

}
