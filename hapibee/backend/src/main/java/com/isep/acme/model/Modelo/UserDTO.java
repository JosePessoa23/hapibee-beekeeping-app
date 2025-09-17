package com.isep.acme.model.Modelo;

import com.isep.acme.repositories.dataModels.JPA.RoleJPA;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long userId;

    private String username;

    private String password;

    private String fullName;

    private String nif;

    private String morada;


    public UserDTO(Long userId, String username, String password, String fullName, String nif, String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
