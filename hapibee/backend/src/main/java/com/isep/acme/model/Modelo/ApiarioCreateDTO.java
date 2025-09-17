package com.isep.acme.model.Modelo;

public class ApiarioCreateDTO {

    public Long apiarioId;

    public String nome_apiario;

    public boolean approved;

    public String flora;

    public double proximidade_agua;

    public double latitude;

    public double longitude;


    public ApiarioCreateDTO(Long apiarioId, String nome_apiario, String flora, double proximidade_agua, double latitude, double longitude) {
        this.apiarioId = apiarioId;
        this.nome_apiario = nome_apiario;
        this.approved = approved;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ApiarioCreateDTO() {
    }

    public Long getApiarioId() {
        return apiarioId;
    }

    public void setApiarioId(Long apiarioId) {
        this.apiarioId = apiarioId;
    }

    public String getNome_apiario() {
        return nome_apiario;
    }

    public void setNome_apiario(String nome_apiario) {
        this.nome_apiario = nome_apiario;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getFlora() {
        return flora;
    }

    public void setFlora(String flora) {
        this.flora = flora;
    }

    public double getProximidade_agua() {
        return proximidade_agua;
    }

    public void setProximidade_agua(double proximidade_agua) {
        this.proximidade_agua = proximidade_agua;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "ApiarioCreateDTO{" +
                "apiarioId=" + apiarioId +
                ", nome_apiario='" + nome_apiario + '\'' +
                ", approved=" + approved +
                ", flora='" + flora + '\'' +
                ", proximidade_agua=" + proximidade_agua +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
