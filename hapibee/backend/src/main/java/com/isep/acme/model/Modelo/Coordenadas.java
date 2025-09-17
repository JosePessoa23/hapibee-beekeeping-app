package com.isep.acme.model.Modelo;

import com.isep.acme.model.Modelo.exceptions.LatitudeInvalidaException;

public class Coordenadas {

    private  double latitude;

    private  double longitude;

    public Coordenadas(double latitude, double longitude) throws LatitudeInvalidaException {
        CheckCoordenadas(latitude,longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coordenadas() {
    }

    private void CheckCoordenadas(double latitude , double longitude) throws LatitudeInvalidaException {
        int limiteLatitudeSuperior = 90;
        int limiteLatitudeInferior = -90;
        int limiteLongitudeSuperior = 180;
        int limiteLongitudeInferior = -180;
        if ( latitude > limiteLatitudeSuperior || latitude < limiteLatitudeInferior ){
            throw new LatitudeInvalidaException();
        }
        if ( longitude > limiteLongitudeSuperior || longitude < limiteLongitudeInferior ){
            throw new LatitudeInvalidaException();
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
