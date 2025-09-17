package com.isep.acme.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Colmeia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long colmeiaId;

    @Column(nullable = false)
    private Long numeroColmeia;

    private Long colmeia_mae;

    @Column(nullable = false)
    private int alças;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String dataRainha;

    @OneToMany(mappedBy = "colmeia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inspeção> registos;


    public Colmeia(int alças, String tipo, String dataRainha) throws ParseException {

        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }


    public Colmeia(Long numeroColmeia, int alças, String tipo, String dataRainha) throws ParseException {
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }

    public Colmeia(Long numeroColmeia, int alças, String tipo, String dataRainha,Long colmeiaIdMae) throws ParseException {
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
        this.colmeia_mae = colmeiaIdMae;
    }

    public Colmeia(Long colmeiaId, Long numeroColmeia, int alças, String tipo, String dataRainha) {
        this.colmeiaId = colmeiaId;
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }

    public Colmeia() {

    }

    public Colmeia(ColmeiaDTO colmeiaDTO) throws ParseException {
        this.numeroColmeia = colmeiaDTO.getNumeroColmeia();
        this.alças = colmeiaDTO.getAlças();
        this.tipo = colmeiaDTO.getTipo();
        this.dataRainha = colmeiaDTO.getDataRainha();
    }

    public Colmeia(ColmeiaDTO colmeiaDTO,Long colmeia_pai) throws ParseException {
        this.numeroColmeia = colmeiaDTO.getNumeroColmeia();
        this.alças = colmeiaDTO.getAlças();
        this.tipo = colmeiaDTO.getTipo();
        this.dataRainha = colmeiaDTO.getDataRainha();
        this.colmeia_mae = colmeia_pai;
    }

    public Long getColmeiaId() {
        return colmeiaId;
    }

    public int getAlças() {
        return alças;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDataRainha() {
        return dataRainha;
    }

    public void setAlças(int alças) {
        this.alças = alças;
    }

    public Long getNumeroColmeia() {
        return numeroColmeia;
    }

    public void setColmeia_pai(Long colmeia_pai) {
        this.colmeia_mae = colmeia_pai;
    }

    public ColmeiaDTO toDTO(){
        return new ColmeiaDTO(colmeiaId,numeroColmeia,alças,colmeia_mae,tipo,dataRainha.toString());
    }

    @Override
    public String toString() {
        return "Colmeia{" +
                "colmeiaId=" + colmeiaId +
                ", numeroColmeia=" + numeroColmeia +
                ", alças=" + alças +
                ", tipo='" + tipo + '\'' +
                ", dataRainha=" + dataRainha +
                '}';
    }

    public List<Inspeção> getRegistos() {
        return registos;
    }

    public void setRegistos(List<Inspeção> registos) {
        this.registos = registos;
    }
}

