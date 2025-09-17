package com.isep.acme.controllers;


import com.isep.acme.model.ApiarioCreateDTO;
import com.isep.acme.model.ApiarioDTO;
import com.isep.acme.model.Product;
import com.isep.acme.model.TransumanciaDTO;
import com.isep.acme.services.ApiarioService;
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

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Tag(name = "Apiario", description = "Endpoints for managing  apiarios")
@RestController
@RequestMapping("/apiarios")
public class ApiarioController {


    private static final Logger logger = LoggerFactory.getLogger(ApiarioController.class);


    @Autowired
    private ApiarioService service;



    @Operation(summary = "creates a apiario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiarioCreateDTO> create(@RequestBody ApiarioCreateDTO apiario, @RequestHeader("Authorization") String token) {

        try {
            // Decodificar o token JWT
            JWT jwt = JWTParser.parse(token);

            // Verificar a assinatura (opcional, dependendo do seu caso)
            // jwt.verify(new MACVerifier(chaveSecreta));

            // Obter as informações do token
            JWTClaimsSet claims = jwt.getJWTClaimsSet();

            String claim = (String) claims.getClaim("sub");
            String[] partes = claim.split(",");
            String userid = partes[1];


            final ApiarioCreateDTO product = service.create(apiario,userid);
            return new ResponseEntity<ApiarioCreateDTO>(product, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }

    @PutMapping(value = "/dgav/{apiarioId}/{approved}")
    public boolean Update(@PathVariable final Long apiarioId,@PathVariable final boolean approved) {

        return service.update(apiarioId,approved);
    }

    @PutMapping(value = "/controlada/{apiarioId}/{approved}")
    public ApiarioDTO Update2(@PathVariable final Long apiarioId, @PathVariable final boolean approved) {

        return service.update2(apiarioId,approved);
    }


    @Operation(summary = "deletes a apiario")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") final Long id ){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
