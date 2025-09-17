package com.isep.acme.model;

import com.isep.acme.model.exceptions.ApiarioException;
import com.isep.acme.model.exceptions.LatitudeInvalidaException;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Transumancia {

    @Id
    private Long apiarioId;
    private boolean approved;

    @Column(nullable = false)
    @Embedded
    private Coordenadas coordenadas;

    public String nome_apiario;

    public String flora;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Colmeia> colmeiaList;

    protected Transumancia() {

    }

    public Transumancia(Long apiarioId, double latitude , double longitude,String nome_apiario, String flora,List<Colmeia> colmeiaList) throws ApiarioException {
        this.apiarioId= apiarioId;
        this.coordenadas = new Coordenadas(latitude,longitude);
        this.approved=false;
        this.nome_apiario=nome_apiario;
        this.flora=flora;
        this.colmeiaList=colmeiaList;
    }

    public Transumancia(TransumanciaDTO transumanciaDTO) throws LatitudeInvalidaException, ParseException {
        this.apiarioId= Long.parseLong(transumanciaDTO.apiarioId);
        this.coordenadas = new Coordenadas(transumanciaDTO.latitude,transumanciaDTO.longitude);
        this.approved=transumanciaDTO.approved;
        this.nome_apiario=transumanciaDTO.nome_apiario;
        this.flora=transumanciaDTO.flora;
        this.colmeiaList = new ArrayList<>();
        for (ColmeiaDTO colmeiaDTO: transumanciaDTO.colmeiaList) {
            this.colmeiaList.add(new Colmeia(colmeiaDTO));
        }
    }


    public void setApproved() {
        this.approved = true;
    }

    public TransumanciaDTO toDTO(){
        List<ColmeiaDTO> pDto = new ArrayList();
        for (Colmeia pd:colmeiaList) {
            pDto.add(pd.toDTO());
        }
        return new TransumanciaDTO(apiarioId.toString(),coordenadas.getLatitude(),coordenadas.getLongitude(),nome_apiario,flora,pDto,approved);
    }

    public Long getApiarioId() {
        return apiarioId;
    }

    public void setApiarioId(Long apiarioId) {
        this.apiarioId = apiarioId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getNome_apiario() {
        return nome_apiario;
    }

    public void setNome_apiario(String nome_apiario) {
        this.nome_apiario = nome_apiario;
    }

    public String getFlora() {
        return flora;
    }

    public void setFlora(String flora) {
        this.flora = flora;
    }

    public List<Colmeia> getColmeiaList() {
        return colmeiaList;
    }

    public void setColmeiaList(List<Colmeia> colmeiaList) {
        this.colmeiaList = colmeiaList;
    }
}
