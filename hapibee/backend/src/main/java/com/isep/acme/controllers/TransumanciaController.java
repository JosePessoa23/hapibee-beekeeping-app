package com.isep.acme.controllers;

import com.isep.acme.model.Modelo.ApiarioDTO;
import com.isep.acme.model.Modelo.TransumanciaDTO;
import com.isep.acme.model.UserView;
import com.isep.acme.services.ApiarioService;
import com.isep.acme.services.TransumanciaService;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Transumancia", description = "Endpoints for managing Transumancias")
@RestController
@RequestMapping("/transumancia")
public class TransumanciaController {


    private static final Logger logger = LoggerFactory.getLogger(ApiarioController.class);


    @Autowired
    private TransumanciaService service;

    @Operation(summary = "creates a apiario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransumanciaDTO create(@RequestBody TransumanciaDTO transumancia) {

        try {
            return service.create(transumancia);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }

    @PutMapping(value = "/{apiarioId}/{latitude}/{longitude}")
    public ApiarioDTO Update(@PathVariable final String apiarioId,@PathVariable final double latitude,@PathVariable final double longitude) {

        return service.update(apiarioId,latitude,longitude);
    }

}

