package com.isep.acme.repositories;


import com.isep.acme.model.Product;
import com.isep.acme.model.Transumancia;
import com.isep.acme.model.TransumanciaDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TransumanciaRepository extends CrudRepository<Transumancia, Long> {
    Transumancia save(Transumancia product);

    @Query("SELECT p FROM Transumancia p WHERE p.apiarioId=:apiarioId")
    Optional<Transumancia> findById(Long apiarioId);

}
