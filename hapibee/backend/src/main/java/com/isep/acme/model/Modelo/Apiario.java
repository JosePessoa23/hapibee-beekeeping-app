package com.isep.acme.model.Modelo;

import com.isep.acme.model.Modelo.exceptions.ApiarioException;
import com.isep.acme.model.Modelo.exceptions.LatitudeInvalidaException;
import com.isep.acme.model.User;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Apiario {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long apiarioId;

    @Column(nullable = false)
    private String nome_apiario;

    @ManyToOne // Muitos Apiarios para Um User
    @JoinColumn(name = "user_id")
    private UserJPA user;

    @Column(nullable = false)
    private String flora;

    @Column(nullable = false)
    private double proximidade_agua;

    private boolean approved=false;

    @Column(nullable = false)
    @Embedded
    private Coordenadas coordenadas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apiarioId")
    private List<Colmeia> colmeiaList;

    public Apiario(String nome_apiario,String flora, double proximidade_agua, double latitude , double longitude,UserJPA user) throws ApiarioException {
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.coordenadas = new Coordenadas(latitude,longitude);
        this.user = user;
    }

    public Apiario(ApiarioDTO apiarioDTO) throws LatitudeInvalidaException, ParseException {
        this.flora = apiarioDTO.flora;
        this.nome_apiario = apiarioDTO.nome_apiario;
        this.proximidade_agua = apiarioDTO.proximidade_agua;
        this.coordenadas = new Coordenadas(apiarioDTO.latitude,apiarioDTO.longitude);
        this.colmeiaList = new ArrayList<>();
        for (ColmeiaDTO colmeiaDTO: apiarioDTO.colmeiaList) {
            this.colmeiaList.add(new Colmeia(colmeiaDTO));
        }

    }

    public Apiario(ApiarioCreateDTO apiarioDTO) throws LatitudeInvalidaException, ParseException {
        this.flora = apiarioDTO.flora;
        this.nome_apiario = apiarioDTO.nome_apiario;
        this.proximidade_agua = apiarioDTO.proximidade_agua;
        this.coordenadas = new Coordenadas(apiarioDTO.latitude,apiarioDTO.longitude);
    }

    public Apiario() {

    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ApiarioDTO toDTO(){
        return new ApiarioDTO(apiarioId,nome_apiario,flora,proximidade_agua,coordenadas.getLatitude(),coordenadas.getLongitude(),colmeiaList,approved,user);
    }

    public ApiarioCreateDTO toDTOCreate(){
        return new ApiarioCreateDTO(apiarioId,nome_apiario,flora,proximidade_agua,coordenadas.getLatitude(),coordenadas.getLongitude());
    }

    public void setUser(UserJPA user) {
        this.user = user;
    }

    public void setProximidade_agua(double proximidade_agua) {
        this.proximidade_agua = proximidade_agua;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        return "Apiario{" +
                "apiarioId=" + apiarioId +
                ", nome_apiario='" + nome_apiario + '\'' +
                ", flora='" + flora + '\'' +
                ", proximidade_agua=" + proximidade_agua +
                ", coordenadas=" + coordenadas +
                ", colmeiaList=" + colmeiaList +
                '}';
    }

    public Long getApiarioId() {
        return apiarioId;
    }

    public void addColmeia(Colmeia colmeia){
        colmeiaList.add(colmeia);
    }
}
