package com.isep.acme.controllers;

import com.isep.acme.model.Modelo.ApiarioDTO;
import com.isep.acme.model.Modelo.ColmeiaDTO;
import com.isep.acme.services.ApiarioService;
import com.isep.acme.services.ColmeiaService;
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

@Tag(name = "Colmeia", description = "Endpoints for managing  colmeias")
@RestController
@RequestMapping("/colmeia")
public class ColmeiaController {

    private static final Logger logger = LoggerFactory.getLogger(ColmeiaController.class);


    @Autowired
    private ColmeiaService service;

    @Operation(summary = "desdobramento de uma colmeia")
    @PostMapping(value = "/{id}/{alcas}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ColmeiaDTO> desdobramento(@PathVariable("id") final Long id,@PathVariable("alcas") final int alcas) {

        try {
            final ColmeiaDTO product = service.desdobramento(id,alcas);
            return new ResponseEntity<ColmeiaDTO>(product, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }
}
