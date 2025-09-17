package com.isep.acme.repositories.dataModels.JPA;


import com.isep.acme.model.Modelo.UserDTO;
import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UserJPA {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<RoleJPA> authorities = new HashSet<>();

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

    protected UserJPA() {}

    public UserJPA(final String username, final String password){
        this.username = username;
        this.password = password;
    }

    public UserJPA(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        setNif(user.getNif());
        this.morada = user.getMorada();
        for(Role r: user.getAuthorities()){
            addAuthority(new RoleJPA(r.getAuthority()));
        }
        this.codigo_Postal = user.getCodigo_Postal();
        this.phonenumber = user.getPhonenumber();
        this.numero_Apicultor = user.getNumero_Apicultor();
    }

    public UserJPA(User user,String id){
        this.userId = Long.parseLong(id);
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        setNif(user.getNif());
        this.morada = user.getMorada();
        for(Role r: user.getAuthorities()){
            addAuthority(new RoleJPA(r.getAuthority()));
        }
    }


    public UserJPA(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(RoleJPA r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if(nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }

    public User toModel(){
        User user = new User(this.userId.toString(),this.username, this.password, this.fullName, this.nif, this.morada,this.codigo_Postal,this.phonenumber,this.numero_Apicultor);
        for(RoleJPA r: this.authorities){
            user.addAuthority(new Role(r.getAuthority()));
        }
        return user;
    }

    public UserDTO toDTO(){
        return new UserDTO(this.userId,this.username,this.password,this.fullName,this.nif,this.morada);
    }

    @Override
    public String toString() {
        return "UserJPA{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", authorities=" + authorities +
                ", nif='" + nif + '\'' +
                ", morada='" + morada + '\'' +
                '}';
    }
}
