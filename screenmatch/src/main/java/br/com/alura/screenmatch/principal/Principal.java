package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConverteDados converteDados = new ConverteDados();

    // Padrão de definição de Constantes em Java, Letras Maiusculas e "_" para separar palavras
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=9b7ce7ae";
    private final String TEMPORADA = "&season=";

    public void exibirMenu(){
        System.out.println("Digite a série que deseja buscas: ");
        var entrada = scanner.nextLine();
        imprimir(entrada);
    }

    public void imprimir(String entrada){

        //.replace serve para trocar o espaço " " da entrada pelo símbolo de "+"
        var json = consumoAPI.obterDados(ENDERECO + entrada.replace(" ", "+") + API_KEY);

        DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        DadosTemporada dadosTemporada;
        for (int i = 1; i <= dadosSerie.numeroTemporadas(); i++) {
            json = consumoAPI.obterDados(ENDERECO + entrada.replace(" ", "+") + TEMPORADA + i + API_KEY);
            dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            System.out.println(dadosTemporada);
        }

    }
}
