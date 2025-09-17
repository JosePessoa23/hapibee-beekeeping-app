package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Modelo.Apiario;
import com.isep.acme.model.Modelo.Colmeia;

import java.util.Optional;

public interface IColmeiaRepository {

    Colmeia save(Colmeia colmeia);

    Optional<Colmeia> findById(Long colmeiaId);
}
