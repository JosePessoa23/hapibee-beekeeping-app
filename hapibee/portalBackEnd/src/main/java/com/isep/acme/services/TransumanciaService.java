package com.isep.acme.services;

import com.isep.acme.model.ApiarioDTO;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.TransumanciaDTO;

public interface TransumanciaService {
    TransumanciaDTO create(final TransumanciaDTO transumancia);

    boolean update(String apiarioId,boolean approved);

    TransumanciaDTO update2(String apiarioId,boolean approved);

    Iterable<TransumanciaDTO> getAll();
}
