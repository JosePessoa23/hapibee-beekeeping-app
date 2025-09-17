package com.isep.acme.model;

import java.util.List;

public class TransumanciaDTO {

    public String apiarioId;

    public double latitude;

    public double longitude;

    public boolean approved;

    public String nome_apiario;

    public String flora;

    public List<ColmeiaDTO> colmeiaList;

    protected TransumanciaDTO(){

    }


    public TransumanciaDTO(String apiarioId, double latitude,double longitude,String nome_apiario, String flora,List<ColmeiaDTO> colmeiaList) {
        this.apiarioId = apiarioId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome_apiario=nome_apiario;
        this.flora=flora;
        this.colmeiaList=colmeiaList;
        this.approved=false;
    }

    public TransumanciaDTO(String apiarioId, double latitude,double longitude,String nome_apiario, String flora,List<ColmeiaDTO> colmeiaList,boolean approved) {
        this.apiarioId = apiarioId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome_apiario=nome_apiario;
        this.flora=flora;
        this.colmeiaList=colmeiaList;
        this.approved=approved;
    }

    public String getApiarioId() {
        return apiarioId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setApiarioId(String apiarioId) {
        this.apiarioId = apiarioId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "TransumanciaDTO{" +
                "apiarioId='" + apiarioId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
