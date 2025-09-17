package com.isep.acme.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Getter
@Setter
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private String userId; //verificar long

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

    @Column(nullable = false)
    private String codigo_Postal;

    @Column(nullable = false,unique = true)
    private Long phonenumber;

    @Column(nullable = false,unique = true)
    private String numero_Apicultor;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected User() {}

    public User(final String username, final String password){
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password, final String fullName, final String nif, final String morada, final String codigo_Postal,final Long phonenumber,final String numero_Apicultor) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
        this.codigo_Postal = codigo_Postal;
        this.phonenumber = phonenumber;
        this.numero_Apicultor = numero_Apicultor;

    }

    //verificar long
    public User(String userId,final String username, final String password, final String fullName, final String nif, final String morada, final String codigo_Postal,final Long phonenumber,final String numero_Apicultor) {
        this.userId = userId; // +""
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
        this.codigo_Postal = codigo_Postal;
        this.phonenumber = phonenumber;
        this.numero_Apicultor = numero_Apicultor;
    }



    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if(nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }
    //verificar long
    public String getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", authorities=" + authorities +
                ", nif='" + nif + '\'' +
                ", morada='" + morada + '\'' +
                ", codigoPostal='" + codigo_Postal + '\'' +
                ", nApicultor='" + numero_Apicultor + '\'' +
                ", tele='" + phonenumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(fullName, user.fullName) && Objects.equals(authorities, user.authorities) && Objects.equals(nif, user.nif) && Objects.equals(morada, user.morada);
    }
}

