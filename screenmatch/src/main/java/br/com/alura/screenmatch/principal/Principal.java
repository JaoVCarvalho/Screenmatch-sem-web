package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episode;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        aprendendoStream();
    }
    public void imprimir(String entrada){

        System.out.println("\nDescrição da Série: " + entrada +"\n");
        DadosSerie dadosSerie = converteSerie(entrada);
        System.out.println(dadosSerie);

        System.out.println("\nLista de Temporadas: \n");
        List<DadosTemporada> temporadas = converteTemporada(entrada);
        temporadas.forEach(System.out::println);

        //Exemplo de função Lambda, principalmente utilizada em coleções
        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo)))

        //Exemplo de métodos de referência em Java no contexto de funções Lambdas
        //temporadas.forEach(t -> System.out.println(t)) -> temporadas.forEach(System.out::println)

        List<Episode> completeListEpisode = gerandoListaEpisodios(entrada);

        System.out.println("\nLista completa de Episódios: \n");
        completeListEpisode.forEach(System.out::println);

        System.out.println("\nListas dos 5 melhores episódios: \n");
        completeListEpisode.stream()
                .sorted(Comparator.comparing(Episode::getAvaliacao).reversed())
                //.filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\nEnter which year you want to filter the episodes from: ");

        var year = Integer.parseInt(scanner.nextLine());
        LocalDate searchDate = LocalDate.of(year, 1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println();
        completeListEpisode.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(searchDate))
                .forEach(e -> System.out.println(
                        "Season: " + e.getTemporada() +
                        " || Title: " + e.getTitulo() +
                        " || Released Date:  " + e.getDataLancamento().format(formatter)
                ));
        System.out.println();
    }

    public DadosSerie converteSerie(String entrada){

        //.replace serve para trocar o espaço " " da entrada pelo símbolo de "+"
        var json = consumoAPI.obterDados(ENDERECO + entrada.replace(" ", "+") + API_KEY);

        return converteDados.obterDados(json, DadosSerie.class);
    }

    public List<DadosTemporada> converteTemporada(String entrada){

        DadosSerie dadosSerie = converteSerie(entrada);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.numeroTemporadas(); i++) {
            var json = consumoAPI.obterDados(ENDERECO + entrada.replace(" ", "+") + TEMPORADA + i + API_KEY);
            temporadas.add(converteDados.obterDados(json, DadosTemporada.class));
        }
        return temporadas;
    }

    // A função ".stream" permite um sequência de operações encadeadas
    public void aprendendoStream(){
        List<String> nomes = Arrays.asList("João", "Paulo", "Patricia", "Diogo", "Mariana");

        nomes.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("J"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    public List<Episode> gerandoListaEpisodios(String entrada){

        List<DadosTemporada> temporadas = converteTemporada(entrada);

        List<Episode> listaCompletaEpisodios = temporadas.stream()
                        .flatMap(t -> t.listaEpisodio().stream()
                                .map(d -> new Episode(t.numeroTemporada(), d)))
                .collect(Collectors.toList());
        //.toList -> Cria uma lista imutável, diferente do .collect que é mutável

        return listaCompletaEpisodios;
        }
}
