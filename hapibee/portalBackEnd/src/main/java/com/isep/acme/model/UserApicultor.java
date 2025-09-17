package com.isep.acme.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class UserApicultor {

    String userId;

    String fullName;

    String nif;

    String morada;

    String codigo_Postal;

    String phonenumber;

    String numero_Apicultor;

}
