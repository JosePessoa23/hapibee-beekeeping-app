package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Modelo.Inspeção;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistoRepository {
    List<Inspeção> findAllByApicultorId(String apicultorId);

    Inspeção save(Inspeção inspeção);
}
