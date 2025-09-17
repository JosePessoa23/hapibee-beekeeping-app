package com.isep.acme.model.Modelo;

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
    @Temporal(TemporalType.DATE)
    private Date dataRainha;

    @OneToMany(mappedBy = "colmeia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inspeção> registos;

    public Colmeia(int alças, String tipo, Date dataRainha) {
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }

    public Colmeia(int alças, String tipo, String dataRainha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = formato.parse(dataRainha);
    }

    public Colmeia(Long numeroColmeia, int alças, String tipo, Date dataRainha) {
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }

    public Colmeia(Long numeroColmeia, int alças, String tipo, String dataRainha) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = formato.parse(dataRainha);
    }

    public Colmeia(Long numeroColmeia, int alças, String tipo, String dataRainha,Long colmeiaIdMae) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = formato.parse(dataRainha);
        this.colmeia_mae = colmeiaIdMae;
    }

    public Colmeia(Long colmeiaId, Long numeroColmeia, int alças, String tipo, Date dataRainha) {
        this.colmeiaId = colmeiaId;
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
    }

    public Colmeia() {

    }

    public Colmeia(ColmeiaDTO colmeiaDTO) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.numeroColmeia = colmeiaDTO.getNumeroColmeia();
        this.alças = colmeiaDTO.getAlças();
        this.tipo = colmeiaDTO.getTipo();
        this.dataRainha = formato.parse(colmeiaDTO.getDataRainha());
    }

    public Colmeia(ColmeiaDTO colmeiaDTO,Long colmeia_pai) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.numeroColmeia = colmeiaDTO.getNumeroColmeia();
        this.alças = colmeiaDTO.getAlças();
        this.tipo = colmeiaDTO.getTipo();
        this.dataRainha = formato.parse(colmeiaDTO.getDataRainha());
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

    public Date getDataRainha() {
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
        return new ColmeiaDTO(colmeiaId,numeroColmeia,alças,tipo,dataRainha.toString(),colmeia_mae);
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

