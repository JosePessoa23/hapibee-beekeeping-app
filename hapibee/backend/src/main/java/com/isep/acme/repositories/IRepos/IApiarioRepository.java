package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Modelo.Apiario;
import com.isep.acme.model.Modelo.ApiarioDTO;
import com.isep.acme.model.Modelo.TransumanciaDTO;
import com.isep.acme.model.Modelo.exceptions.LatitudeInvalidaException;
import com.isep.acme.model.Product;

import java.util.List;
import java.util.Optional;

public interface IApiarioRepository {

    List<Apiario> findAll();

    Optional<Apiario> findBySku(String sku);


    void delete(String sku);

    Apiario updateBySku( String sku, Apiario p);

    Apiario save(Apiario product);

    ApiarioDTO transumancia(String apiarioId,double latitude,double longitude) throws LatitudeInvalidaException;

    Optional<Apiario> findById(Long productID);

    Optional<Apiario> findByUserId(String productID);

    Optional<Apiario> findByColmeia(Long productID);


}
