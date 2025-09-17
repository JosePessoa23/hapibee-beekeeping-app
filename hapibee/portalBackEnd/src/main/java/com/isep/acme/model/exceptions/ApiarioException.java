package com.isep.acme.model.exceptions;

public abstract class ApiarioException extends Exception{
    public ApiarioException(String message) {
        super(message);
    }
}
