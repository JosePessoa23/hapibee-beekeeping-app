package com.isep.acme.services;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.model.Product;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.ProductDetailDTO;

import java.util.Optional;

public interface ApiarioService {

    Iterable<ApiarioDTO> getAll();

    Optional<ApiarioDTO> getDetails(final Long sku);

    Iterable<Historico> getHistorico(final Long sku);

    Optional<ApiarioDTO> getByUserId(final String sku);

    ApiarioCreateDTO create(final ApiarioCreateDTO apiario , String userid);

    ApiarioDTO updateBySku(final String sku, final ApiarioDTO product);

    ApiarioDTO addColemia(final Long sku, final ColmeiaDTOList colmeiaDTO);

    void delete(final String sku);
}
