package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

/*
* O @JsonAlias serve apenas para o processo de desserialização, isto é, ler um dado serializado convertendo para um dado
* que pode ser manipulado em programa de computador
*
* O @JsonProperty serve tanto no processo de serialização, quanto no processo de desserialização, isto é, ele tenta ler
* "Title" e tenta escrever "Title", por exemplo
*
* Podemos utilizar uma lista de nomes, como exemplo, ({"Title", "Titulo"}), para serem buscadas na API
* */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora os dados que não queremos ler
public record DadosFilme(@JsonAlias("Title") String titulo,
                         @JsonAlias("Year") Integer ano,
                         @JsonAlias("Runtime") String duracaoFilme,
                         @JsonAlias("imdbRating") String avaliacao) {
}
