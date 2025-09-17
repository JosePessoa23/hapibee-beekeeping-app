package com.isep.acme.model.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Inspeção {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registoId;

    @Temporal(TemporalType.DATE)
    private Date dataRegisto;

    private String apicultorId;

    @ManyToOne
    @JoinColumn(name = "colmeiaId")
    private Colmeia colmeia;

    private String higiene;
    private String tendencia_enxamear;
    private String agressividade;
    private String produtividade;
    private String capacidade_polinizadora;
    private String observacoes;



    public Inspeção() {
    }

    public Inspeção(String apicultorId, String higiene, String tendencia_enxamear, String agressividade, String produtividade, String capacidade_polinizadora, String observacoes) {
        this.apicultorId = apicultorId;
        this.higiene = higiene;
        this.tendencia_enxamear = tendencia_enxamear;
        this.agressividade = agressividade;
        this.produtividade = produtividade;
        this.capacidade_polinizadora = capacidade_polinizadora;
        this.observacoes = observacoes;
        this.dataRegisto = new Date();
    }

    public String getHigiene() {
        return higiene;
    }

    public String getTendencia_enxamear() {
        return tendencia_enxamear;
    }

    public String getAgressividade() {
        return agressividade;
    }

    public String getProdutividade() {
        return produtividade;
    }

    public String getCapacidade_polinizadora() {
        return capacidade_polinizadora;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setColmeia(Colmeia colmeia) {
        this.colmeia = colmeia;
    }

}
