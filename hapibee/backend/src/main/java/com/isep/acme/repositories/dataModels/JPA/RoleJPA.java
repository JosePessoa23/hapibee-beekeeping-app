package com.isep.acme.repositories.dataModels.JPA;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
@AllArgsConstructor

public class RoleJPA implements GrantedAuthority {

    public static final String Admin = "Admin";

    public static final String Mod = "Mod";

    public static final String RegisteredUser = "RegisteredUser";

    private String authority;
}