package com.isep.acme.model.Modelo;

import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class ApiarioDTO {

    public Long apiarioId;

    public String nome_apiario;

    public UserDTO user;

    public boolean approved;

    public String flora;

    public double proximidade_agua;

    public double latitude;

    public double longitude;

    public List<ColmeiaDTO> colmeiaList;

    public ApiarioDTO(Long apiarioId,String nome_apiario ,String flora, double proximidade_agua, double latitude, double longitude) {
        this.apiarioId = apiarioId;
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ApiarioDTO(String flora, double proximidade_agua, double latitude, double longitude) {
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ApiarioDTO(String flora, double proximidade_agua, double latitude, double longitude, List<ColmeiaDTO> colmeiaList) {
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.colmeiaList = colmeiaList;
    }

    public ApiarioDTO(Long apiarioId,String nome_apiario ,String flora, double proximidade_agua, double latitude, double longitude, List<Colmeia> colmeiaaList) {
        this.apiarioId = apiarioId;
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.colmeiaList = new ArrayList<>();
        for (Colmeia colmeia: colmeiaaList) {
            colmeiaList.add(new ColmeiaDTO(colmeia.getColmeiaId(),colmeia.getNumeroColmeia(),colmeia.getAlças(),colmeia.getTipo(),colmeia.getDataRainha().toString()));
        }
    }

    public ApiarioDTO(Long apiarioId, String nome_apiario , String flora, double proximidade_agua, double latitude, double longitude, List<Colmeia> colmeiaaList, boolean approved, UserJPA userJPA) {
        this.apiarioId = apiarioId;
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.colmeiaList = new ArrayList<>();
        for (Colmeia colmeia: colmeiaaList) {
            colmeiaList.add(new ColmeiaDTO(colmeia.getColmeiaId(),colmeia.getNumeroColmeia(),colmeia.getAlças(),colmeia.getTipo(),colmeia.getDataRainha().toString()));
        }
        this.approved = approved;
        this.user = userJPA.toDTO();
    }



    public ApiarioDTO() {
    }

    public Long getApiarioId() {
        return apiarioId;
    }

    public void setApiarioId(Long apiarioId) {
        this.apiarioId = apiarioId;
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

    public List<ColmeiaDTO> getColmeiaDTOList() {
        return colmeiaList;
    }

    public void setColmeiaDTOList(List<ColmeiaDTO> colmeiaDTOList) {
        this.colmeiaList = colmeiaDTOList;
    }

    public String getNome_apiario() {
        return nome_apiario;
    }

    public void setNome_apiario(String nome_apiario) {
        this.nome_apiario = nome_apiario;
    }

    @Override
    public String toString() {
        return  "\nApiário " + nome_apiario + "{"+
                "\nAprovação: " + approved +
                "\nFlora: " + flora +
                "\nProximidade de Agua: " + proximidade_agua +
                "\nLatitude: " + latitude +
                "\nLongitude: " + longitude +
                "\nColmeias: " + colmeiaList +
                "}\n\n";
    }
}
