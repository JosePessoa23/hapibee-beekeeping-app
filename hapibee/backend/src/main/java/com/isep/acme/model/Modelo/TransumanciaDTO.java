package com.isep.acme.model.Modelo;

public class TransumanciaDTO {

    public String apiarioId;

    public double proximidade_agua;

    public double latitude;

    public double longitude;


    public TransumanciaDTO(String apiarioId, double proximidade_agua, double latitude,double longitude) {
        this.apiarioId = apiarioId;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getApiarioId() {
        return apiarioId;
    }

    public double getProximidade_agua() {
        return proximidade_agua;
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

    public void setProximidade_agua(double proximidade_agua) {
        this.proximidade_agua = proximidade_agua;
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
                ", proximidade_agua=" + proximidade_agua +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
