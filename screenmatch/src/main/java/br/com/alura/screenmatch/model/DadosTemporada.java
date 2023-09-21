package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") Integer numeroTemporada,
                             @JsonAlias("Episodes") List<DadosEpisodio> listaEpisodio) {

    public String toString(){
        return "Temporada: " + numeroTemporada + "\n" +
                "Lista de Episódios: {\n" + listaEpisodio + "}\n";
    }
}
