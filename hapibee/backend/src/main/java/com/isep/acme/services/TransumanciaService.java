package com.isep.acme.services;

import com.isep.acme.model.Modelo.ApiarioDTO;
import com.isep.acme.model.Modelo.TransumanciaDTO;

public interface TransumanciaService {

    TransumanciaDTO create(final TransumanciaDTO transumancia);

    ApiarioDTO update(String apiarioId,double latitude,double longitude);
}
