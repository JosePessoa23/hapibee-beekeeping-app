package com.isep.acme.model.Modelo;

import com.isep.acme.model.Modelo.exceptions.ApiarioException;
import com.isep.acme.model.Modelo.exceptions.LatitudeInvalidaException;

import javax.persistence.*;
import java.text.ParseException;


@Entity
public class Transumancia {

        @Id
        private Long apiarioId;

        @Column(nullable = false)
        private double proximidade_agua;

        private boolean approved;

        @Column(nullable = false)
        @Embedded
        private Coordenadas coordenadas;

    protected Transumancia() {

    }

        public Transumancia(Long apiarioId, double proximidade_agua, double latitude , double longitude) throws ApiarioException {
            this.apiarioId= apiarioId;
            this.proximidade_agua = proximidade_agua;
            this.coordenadas = new Coordenadas(latitude,longitude);
            this.approved=false;
        }

        public Transumancia(TransumanciaDTO transumanciaDTO) throws LatitudeInvalidaException {
            this.apiarioId= Long.parseLong(transumanciaDTO.apiarioId);
            this.proximidade_agua = transumanciaDTO.proximidade_agua;
            this.coordenadas = new Coordenadas(transumanciaDTO.latitude,transumanciaDTO.longitude);
            this.approved=false;
        }


        public void setApproved() {
            this.approved = true;
        }

        public TransumanciaDTO toDTO(){
            return new TransumanciaDTO(apiarioId.toString(),proximidade_agua,coordenadas.getLatitude(),coordenadas.getLongitude());
        }



}
