package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    List<Product> findByDesignation(String designation);
    List<Product> findAll();

    Optional<Product> findBySku(String sku);


    void deleteBySku(String sku);

    Product updateBySku( String sku, Product p);

    Product save( Product product);

    Optional<Product> findById(Long productID);

}
