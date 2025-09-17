package com.isep.acme.services;

import com.isep.acme.model.Declaracao;
import com.isep.acme.model.DeclaracaoDTO;
import com.isep.acme.model.TransumanciaDTO;

public interface DeclaracaoService {

    void create(final DeclaracaoDTO declaracaoDTO);
    Iterable<DeclaracaoDTO> getAll();
}
