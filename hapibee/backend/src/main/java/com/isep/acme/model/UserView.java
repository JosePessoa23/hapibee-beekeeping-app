package com.isep.acme.model;

import lombok.Data;

@Data
public class UserView {
    String userId;

    String username;

    String fullName;

    String nif;

    String morada;

    String codigo_Postal;

    String phonenumber;

    String numero_Apicultor;

    @Override
    public String toString() {
        return "Nome completo: " + fullName +
                "\nNif: " + nif +
                "\nMorada: " + morada +
                "\nCodigo Postal: " + codigo_Postal +
                "\nNúmero de Telemóvel: " + phonenumber +
                "\nNúmero de registo do Apicultor: " + numero_Apicultor;
    }
}
