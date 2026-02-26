package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosResposta {

    @JsonAlias("results")
    private List<DadosLivro> resultados;

    public List<DadosLivro> getResultados() {
        return resultados;
    }

    public void setResultados(List<DadosLivro> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "Resultados encontrados: " + resultados;
    }
}