package com.isep.acme.controllers;

import com.isep.acme.model.Declaracao;
import com.isep.acme.model.DeclaracaoDTO;
import com.isep.acme.model.TransumanciaDTO;
import com.isep.acme.services.DeclaracaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Declaracao", description = "Endpoints for managing declaração anual")
@RestController
@RequestMapping("/declaracao")
public class DeclaracaoController {
    private static final Logger logger = LoggerFactory.getLogger(DeclaracaoController.class);


    @Autowired
    private DeclaracaoService service;

    @GetMapping
    public ResponseEntity<Iterable<DeclaracaoDTO>> getCatalog() {
        final var declaracaos = service.getAll();

        return ResponseEntity.ok().body( declaracaos );
    }


    @Operation(summary = "creates a declaração anual")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public boolean create(@RequestBody DeclaracaoDTO declaracao) {
        service.create(declaracao);
        return true;

    }

}
