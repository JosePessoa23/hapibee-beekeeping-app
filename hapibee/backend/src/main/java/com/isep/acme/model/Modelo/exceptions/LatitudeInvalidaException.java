package com.isep.acme.model.Modelo.exceptions;

public class LatitudeInvalidaException extends ApiarioException{
    public LatitudeInvalidaException() {super("Latitude fora dos limites [-90,90]");}
}
