package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosFilme;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
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
	public void run(String... args) throws Exception {
		var consumoAPI = new ConsumoAPI();

		var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=Narnia&apikey=9b7ce7ae");
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosFilme dados = conversor.obterDados(json, DadosFilme.class);
		System.out.println(dados);
	}
}
