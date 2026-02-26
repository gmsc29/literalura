package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosLivro {

    @JsonAlias("title")
    private String titulo;

    @JsonAlias("authors")
    private List<DadosAutor> autores;

    @JsonAlias("languages")
    private List<String> idiomas;

    @JsonAlias("download_count")
    private Double numeroDeDownloads;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DadosAutor> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Double numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo +
                " | Idiomas: " + idiomas +
                " | Downloads: " + numeroDeDownloads +
                "\n   " + autores;
    }
}