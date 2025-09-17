package com.isep.acme.model.Modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Historico {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long apiarioId;

    private Long colmeiaId;

    private Date date;

    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Historico(Long apiarioId, Long colmeiaId, String tipo) {
        this.apiarioId = apiarioId;
        this.colmeiaId = colmeiaId;
        this.date = new Date();
        this.tipo = tipo;
    }

    public Historico(Long apiarioId, String tipo) {
        this.apiarioId = apiarioId;
        this.date = new Date();
        this.tipo = tipo;
    }

    public Historico() {

    }

    public Long getApiarioId() {
        return apiarioId;
    }

    public Long getColmeiaId() {
        return colmeiaId;
    }

    public Date getDate() {
        return date;
    }

    public String getTipo() {
        return tipo;
    }
}
