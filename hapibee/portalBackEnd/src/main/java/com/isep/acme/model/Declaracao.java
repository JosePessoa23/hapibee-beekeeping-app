package com.isep.acme.model;

import com.isep.acme.model.exceptions.ApiarioException;
import com.isep.acme.model.exceptions.LatitudeInvalidaException;

import javax.persistence.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Declaracao {

    @Id
    @GeneratedValue
    private Long declaracaoId;

    @Column(nullable = false)
    @Embedded
    public UserApicultor user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Apiario> listapiarios;

    protected Declaracao() {

    }

    public Declaracao(Long declaracaoId, UserApicultor user, List<Apiario> listapiarios) {
        this.declaracaoId = declaracaoId;
        this.user = user;
        this.listapiarios = listapiarios;
    }

    public Declaracao(DeclaracaoDTO declaracaoDTO) throws ApiarioException, ParseException {
        this.user = declaracaoDTO.user;
        List<Colmeia> colmeiaList = new ArrayList<>();

        this.listapiarios = new ArrayList<>();
        for (ApiarioDTO apiario: declaracaoDTO.listapiarios) {
            for (ColmeiaDTO colmeiaDTO: apiario.colmeiaList) {
                colmeiaList.add(new Colmeia(colmeiaDTO));
            }
            listapiarios.add(new Apiario(apiario.getNome_apiario(),apiario.getFlora(),apiario.getProximidade_agua(),apiario.getLatitude(),apiario.getLongitude(),apiario.getUser(),apiario.isApproved(),colmeiaList));
        }
    }



    public Declaracao( UserApicultor user, List<Apiario> listapiarios) {
        this.user = user;
        this.listapiarios = listapiarios;
    }

    public DeclaracaoDTO toDTO(){
        List<ApiarioDTO> pDto = new ArrayList();
        for (Apiario pd:listapiarios) {
            pDto.add(pd.toDTO());
        }
        return new DeclaracaoDTO(user,pDto);
    }

    @Override
    public String toString() {
        return "Declaracao{" +
                "declaracaoId=" + declaracaoId +
                ", user=" + user +
                ", listapiarios=" + listapiarios +
                '}';
    }
}
