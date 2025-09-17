package com.isep.acme.controllers;


import com.isep.acme.model.Modelo.*;
import com.isep.acme.model.Product;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.services.ApiarioService;
import com.isep.acme.services.ProductService;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Operation(summary = "gets All dos apiarios")
    @GetMapping
    public ResponseEntity<Iterable<ApiarioDTO>> getCatalog() {
        final var apiarios = service.getAll();

        return ResponseEntity.ok().body( apiarios );
    }



    @Operation(summary = "finds apiario by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiarioDTO> getProductBySku(@PathVariable("id") final Long id) {

        final Optional<ApiarioDTO> apiario = service.getDetails(id);

        if( apiario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(apiario.get());
    }

    @Operation(summary = "finds apiario by id")
    @GetMapping(value = "/{id}/historico")
    public ResponseEntity<Iterable<Historico>> getHistorico(@PathVariable("id") final Long id) {

        final Iterable<Historico> apiario = service.getHistorico(id);

        if( apiario == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(apiario);
    }

    @Operation(summary = "finds apiario by id")
    @GetMapping(value = "/userId")
    public ResponseEntity<ApiarioDTO> getProductByUserId(@RequestHeader("Authorization") String token) throws UnsupportedEncodingException {

        Optional<ApiarioDTO> apiario = Optional.empty();

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

            apiario = service.getByUserId(userid);

        } catch (Exception e) {
            // Lidar com exceções, como token expirado, inválido ou assinatura incorreta
            e.printStackTrace();
        }


        if( apiario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(apiario.get());
    }

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

    @Operation(summary = "creates a apiario")
    @PostMapping(value = "/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiarioCreateDTO> create2(@RequestBody ApiarioCreateDTO apiario,@PathVariable("username") final String username) {

        try {

            final ApiarioCreateDTO product = service.create(apiario,username);
            return new ResponseEntity<ApiarioCreateDTO>(product, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a apiario")
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ApiarioDTO> Update(@PathVariable("id") final String id, @RequestBody final ApiarioDTO apiarioDTO) {

        final ApiarioDTO productDTO = service.updateBySku(id, apiarioDTO);

        if( productDTO == null )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "adiciona uma colmeia")
    @PatchMapping(value = "/{id}/colmeia")
    public ResponseEntity<ApiarioDTO> addColmeia(@PathVariable("id") final Long id, @RequestBody final ColmeiaDTOList colmeiaDTO) {

        final ApiarioDTO productDTO = service.addColemia(id, colmeiaDTO);

        if( productDTO == null )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "deletes a apiario")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") final String id ){

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
