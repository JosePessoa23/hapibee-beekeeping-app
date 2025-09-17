package com.isep.acme.services;

import com.isep.acme.model.Modelo.InspecaoDTO;
import com.isep.acme.model.Modelo.Inspeção;

import java.util.List;

public interface RegistoService {

    List<Inspeção> findAll();

    Inspeção adicionarInspecao(Long colmeiaId,String userId,InspecaoDTO inspecaoAdicionada);
}
