package com.isep.acme.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class UserApiario {

    String userId;

    String username;

    String fullName;

    String nif;

    String morada;

}
