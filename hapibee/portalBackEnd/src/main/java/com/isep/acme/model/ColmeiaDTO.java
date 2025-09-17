package com.isep.acme.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.List;

public class ColmeiaDTO {

    public Long id;

    public Long numeroColmeia;

    public int alças;

    public Long colmeia_mae;

    public String tipo;

    public String dataRainha;

    protected ColmeiaDTO(){

    }

    public ColmeiaDTO(Long id, Long numeroColmeia, int alças,Long colmeia_mae, String tipo, String dataRainha) {
        this.numeroColmeia = numeroColmeia;
        this.alças = alças;
        this.tipo = tipo;
        this.dataRainha = dataRainha;
        this.colmeia_mae = colmeia_mae;
        this.id = id;
    }

    public int getAlças() {
        return alças;
    }

    public void setAlças(int alças) {
        this.alças = alças;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataRainha() {
        return dataRainha;
    }

    public void setDataRainha(String dataRainha) {
        this.dataRainha = dataRainha;
    }

    public Long getNumeroColmeia() {
        return numeroColmeia;
    }

    public void setNumeroColmeia(Long numeroColmeia) {
        this.numeroColmeia = numeroColmeia;
    }

    @Override
    public String toString() {
        return "\nColmeia nº" + numeroColmeia + "{"+
                "\nNúmero de alças: " + alças +
                "\nTipo: " + tipo +
                "\nData de nascimento da rainha: " + dataRainha +
                "}\n";
    }

}
