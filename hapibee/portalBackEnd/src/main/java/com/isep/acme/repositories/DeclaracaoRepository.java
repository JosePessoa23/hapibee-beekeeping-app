package com.isep.acme.repositories;

import com.isep.acme.model.Declaracao;
import com.isep.acme.model.Product;
import com.isep.acme.model.Transumancia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeclaracaoRepository extends CrudRepository<Declaracao, Long> {

    Declaracao save(Declaracao product);

    @Query("SELECT p FROM Declaracao p")
    Optional<Product> getAll();
}
