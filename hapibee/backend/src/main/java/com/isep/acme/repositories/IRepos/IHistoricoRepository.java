package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Modelo.Apiario;
import com.isep.acme.model.Modelo.Historico;

import java.util.List;

public interface IHistoricoRepository {

    Historico save(Historico product);

    List<Historico> getByApiarioId(Long apiarioId);
}
