package com.isep.acme.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isep.acme.model.*;
import com.isep.acme.repositories.IRepos.IProductRepository;
import com.isep.acme.repositories.ProductRepository;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IProductRepository repository;

    private final ProductMapper mapper = new ProductMapper();

    @Override
    public Optional<Product> getProductBySku( final String sku ) {

        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        if( product.isEmpty() )
            return Optional.empty();
        else
            return Optional.of( product.get().toDto() );
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);

        return mapper.toDTO(p);
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();

        return mapper.toDTO(p);
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @SneakyThrows //Caso seja utilizado o metodo do GeneratorSku2
    @Override
    public ProductDTO create(final Product product){
        try {
            final Product temp = new Product("frgegerge", product.getDesignation(), product.getDescription());
            return mapper.toDTO(repository.save(temp));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {
        
        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if( productToUpdate.isEmpty() ) return null;
        productToUpdate.get().updateProduct(product);
        Product productUpdated = repository.updateBySku(sku, productToUpdate.get());
        
        return mapper.toDTO( productUpdated);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }
}
