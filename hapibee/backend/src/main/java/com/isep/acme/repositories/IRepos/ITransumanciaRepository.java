package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Modelo.Transumancia;
import com.isep.acme.model.Modelo.TransumanciaDTO;

public interface ITransumanciaRepository {

    Transumancia save(Transumancia product);

    TransumanciaDTO update(String apiarioId);

}
