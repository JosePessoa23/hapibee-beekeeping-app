package com.isep.acme.model.Modelo.exceptions;

public abstract class ApiarioException extends Exception{
    public ApiarioException(String message) {
        super(message);
    }
}
