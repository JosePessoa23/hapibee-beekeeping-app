package com.isep.acme.model;

import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ApiarioDTO {

    public Long apiarioId;

    public String nome_apiario;

    public UserApiario user;

    public boolean approved;

    public String flora;

    public double proximidade_agua;

    public double latitude;

    public double longitude;

    public List<ColmeiaDTO> colmeiaList;

    public ApiarioDTO(Long apiarioId,String nome_apiario ,UserApiario user,boolean approved,String flora, double proximidade_agua, double latitude, double longitude,List<ColmeiaDTO> colmeiaList) {
        this.apiarioId = apiarioId;
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
        this.approved=approved;
        this.colmeiaList=colmeiaList;
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

    public UserApiario getUser() {
        return user;
    }

    public void setUser(UserApiario user) {
        this.user = user;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<ColmeiaDTO> getColmeiaList() {
        return colmeiaList;
    }

    public void setColmeiaList(List<ColmeiaDTO> colmeiaList) {
        this.colmeiaList = colmeiaList;
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
