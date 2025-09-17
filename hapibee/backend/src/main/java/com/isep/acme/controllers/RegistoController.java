package com.isep.acme.controllers;

import com.isep.acme.model.Modelo.InspecaoDTO;
import com.isep.acme.model.Modelo.Inspeção;
import com.isep.acme.services.RegistoService;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Registos", description = "Endpoints for managing registos")
@RestController
@RequestMapping("/registos")
public class RegistoController {

    @Autowired
    private RegistoService service;

    @Operation(summary = "findAllRegistos")
    @GetMapping
    public ResponseEntity<List<Inspeção>> findAll() {
        final var registos = service.findAll();
        return ResponseEntity.ok().body(registos);
    }

    @Operation(summary = "criarInspecao")
    @PostMapping(value = "/{colmeiaid}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Inspeção> criarInspecao(@PathVariable("colmeiaid") final Long colmeiaid, @RequestBody InspecaoDTO inspecao, @RequestHeader("Authorization") String token) {

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

            Inspeção adicionarInspecao = service.adicionarInspecao(colmeiaid,userid,inspecao);
            return new ResponseEntity<Inspeção>(adicionarInspecao, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }
}
