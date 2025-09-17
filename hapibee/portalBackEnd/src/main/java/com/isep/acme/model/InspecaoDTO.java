package com.isep.acme.model;

public class InspecaoDTO {

    public String higiene;
    public String tendencia_enxamear;
    public String agressividade;
    public String produtividade;
    public String capacidade_polinizadora;
    public String observacoes;

    public InspecaoDTO() {
    }

    public String getHigiene() {
        return higiene;
    }

    public void setHigiene(String higiene) {
        this.higiene = higiene;
    }

    public String getTendencia_enxamear() {
        return tendencia_enxamear;
    }

    public void setTendencia_enxamear(String tendencia_enxamear) {
        this.tendencia_enxamear = tendencia_enxamear;
    }

    public String getAgressividade() {
        return agressividade;
    }

    public void setAgressividade(String agressividade) {
        this.agressividade = agressividade;
    }

    public String getProdutividade() {
        return produtividade;
    }

    public void setProdutividade(String produtividade) {
        this.produtividade = produtividade;
    }

    public String getCapacidade_polinizadora() {
        return capacidade_polinizadora;
    }

    public void setCapacidade_polinizadora(String capacidade_polinizadora) {
        this.capacidade_polinizadora = capacidade_polinizadora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
