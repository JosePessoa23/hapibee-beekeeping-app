package com.isep.acme.controllers;



import com.isep.acme.model.Modelo.ApiarioDTO;
import com.isep.acme.model.Modelo.DeclaracaoAnualDTO;
import com.isep.acme.services.ApiarioService;
import com.isep.acme.services.DeclaracaoAnualService;
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

@Tag(name = "Declaracao", description = "Endpoints for managing declaração anual")
@RestController
@RequestMapping("/declaracao")
public class DeclaracaoAnualController {

    private static final Logger logger = LoggerFactory.getLogger(DeclaracaoAnualController.class);


    @Autowired
    private DeclaracaoAnualService service;


    @Operation(summary = "creates a declaração anual")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean create(@RequestBody DeclaracaoAnualDTO declaracao) {
            service.create(declaracao);
            return true;

    }

}
