package com.isep.acme.model;


import com.isep.acme.model.exceptions.ApiarioException;
import com.isep.acme.model.exceptions.LatitudeInvalidaException;

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

    @Column(nullable = false)
    @Embedded
    private UserApiario user;

    @Column(nullable = false)
    private String flora;

    @Column(nullable = false)
    private double proximidade_agua;

    private boolean approved;

    @Column(nullable = false)
    @Embedded
    private Coordenadas coordenadas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "apiarioId")
    private List<Colmeia> colmeiaList;

    public Apiario(String nome_apiario,String flora, double proximidade_agua, double latitude , double longitude,UserApiario user,boolean approved,List<Colmeia> colmeiaList) throws ApiarioException {
        this.nome_apiario = nome_apiario;
        this.flora = flora;
        this.proximidade_agua = proximidade_agua;
        this.coordenadas = new Coordenadas(latitude,longitude);
        this.user = user;
        this.approved=approved;
        this.colmeiaList=colmeiaList;
    }

    public Apiario(ApiarioDTO apiarioDTO) throws LatitudeInvalidaException, ParseException {
        this.flora = apiarioDTO.flora;
        this.nome_apiario = apiarioDTO.nome_apiario;
        this.user=apiarioDTO.user;
        this.approved= apiarioDTO.approved;
        this.proximidade_agua = apiarioDTO.proximidade_agua;
        this.coordenadas = new Coordenadas(apiarioDTO.latitude,apiarioDTO.longitude);
        this.colmeiaList = new ArrayList<>();
        for (ColmeiaDTO colmeiaDTO: apiarioDTO.colmeiaList) {
            this.colmeiaList.add(new Colmeia(colmeiaDTO));
        }

    }

    public Apiario(ApiarioCreateDTO apiarioDTO, String userId) throws LatitudeInvalidaException {
        this.flora = apiarioDTO.flora;
        this.nome_apiario = apiarioDTO.nome_apiario;
        this.proximidade_agua = apiarioDTO.proximidade_agua;
        this.coordenadas = new Coordenadas(apiarioDTO.latitude,apiarioDTO.longitude);
        this.user = new UserApiario();
        this.user.username = userId;
    }

    public Apiario() {

    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public ApiarioDTO toDTO(){
        List<ColmeiaDTO> pDto = new ArrayList();
        for (Colmeia pd:colmeiaList) {
            pDto.add(pd.toDTO());
        }
        return new ApiarioDTO(apiarioId,nome_apiario,user,approved,flora,proximidade_agua,coordenadas.getLatitude(),coordenadas.getLongitude(),pDto);
    }

    public ApiarioCreateDTO toDTOCreate(){
        return new ApiarioCreateDTO(apiarioId,nome_apiario,flora,proximidade_agua,coordenadas.getLatitude(),coordenadas.getLongitude());
    }

    public UserApiario getUser() {
        return user;
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
                ", user=" + user +
                ", flora='" + flora + '\'' +
                ", proximidade_agua=" + proximidade_agua +
                ", approved=" + approved +
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
