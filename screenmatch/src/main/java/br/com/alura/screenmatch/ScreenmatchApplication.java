package br.com.alura.screenmatch;

import br.com.alura.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* A interface "CommandLineRunner" permite o desenvolvimento de regras de negócios dentro da classe main
* O método run acaba virando um novo método main
* */

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Principal principal = new Principal();
		principal.exibirMenu();

		System.out.println("Apenas testando");
	}
}
