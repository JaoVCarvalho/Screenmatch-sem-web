package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String titulo,
                            @JsonAlias("Episode") Integer numeroEpisodio,
                            @JsonAlias("imdbRating") String avaliacao,
                            @JsonAlias("Released") String dataLancamento) {

    public String toString(){
        return "Episódio [\n" +
                "Título: " + titulo + "\n" +
                "Número do Episódio: " + numeroEpisodio + "\n" +
                "Data de Lançamento: " + dataLancamento + "\n" +
                "Avaliação: " +  avaliacao + "]\n";
    }
}
