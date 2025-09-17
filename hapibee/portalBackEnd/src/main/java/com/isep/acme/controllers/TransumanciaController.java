package com.isep.acme.controllers;

import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.TransumanciaDTO;
import com.isep.acme.services.TransumanciaService;
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

    private static final Logger logger = LoggerFactory.getLogger(TransumanciaController.class);


    @Autowired
    private TransumanciaService service;

    @GetMapping
    public ResponseEntity<Iterable<TransumanciaDTO>> getCatalog() {
        final var transumancias = service.getAll();

        return ResponseEntity.ok().body( transumancias );
    }

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

    @PutMapping(value = "/dgav/{apiarioId}/{approved}")
    public boolean Update(@PathVariable final String apiarioId,@PathVariable final boolean approved) {

        return service.update(apiarioId,approved);
    }

    @PutMapping(value = "/controlada/{apiarioId}/{approved}")
    public TransumanciaDTO Update2(@PathVariable final String apiarioId,@PathVariable final boolean approved) {

        return service.update2(apiarioId,approved);
    }
}
