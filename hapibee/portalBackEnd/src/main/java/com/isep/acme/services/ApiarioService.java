package com.isep.acme.services;

import com.isep.acme.model.ApiarioCreateDTO;
import com.isep.acme.model.ApiarioDTO;
import com.isep.acme.model.TransumanciaDTO;

import java.util.Optional;

public interface ApiarioService {

    Iterable<ApiarioDTO> getAll();


    ApiarioCreateDTO create(final ApiarioCreateDTO apiario , String userid);

    boolean update(Long apiarioId,boolean approved);

    ApiarioDTO update2(Long apiarioId, boolean approved);

    void delete(final Long sku);
}
