package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.IRepos.IProductRepository;
import com.isep.acme.repositories.dataModels.JPA.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JPAProductRepository implements IProductRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Product> findByDesignation(String designation) {
        try {
            Query query = em.createQuery("SELECT p FROM ProductJPA p WHERE p.designation=:designation");
            query.setParameter("designation", designation);
            List<ProductJPA> pjl = (List<ProductJPA>) query.getResultList();
            List<Product> pl = new ArrayList<>();
            for (ProductJPA pj : pjl) {
                pl.add(pj.toModel());
            }
            return pl;
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        try {
            Query query = em.createQuery("SELECT p FROM ProductJPA p WHERE p.sku=:sku");
            query.setParameter("sku", sku);
            ProductJPA pj = (ProductJPA) query.getSingleResult();
            if (pj == null) {
                return Optional.empty();
            } else {
                return Optional.of(pj.toModel());
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteBySku(String sku) {
        Product p = findBySku(sku).get();
        ProductJPA pj = em.find(ProductJPA.class, Long.parseLong(p.getProductID()));
        em.remove(pj);
    }

    @Override
    @Transactional
    public Product updateBySku(String sku, Product p1) {
        ProductJPA p = em.find(ProductJPA.class,Long.parseLong(p1.getProductID()));
        p.setDescription(p.getDescription());
        p.setDesignation(p.getDesignation());
        return em.merge(p).toModel();
    }

    @Override
    public Optional<Product> findById(Long productID) {
        try {
            Query query = em.createQuery("SELECT p FROM ProductJPA p WHERE p.productID=:id");
            query.setParameter("id", productID);
            ProductJPA pj = (ProductJPA) query.getSingleResult();
            if (pj == null) {
                return Optional.empty();
            } else {
                return Optional.of(pj.toModel());
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }


    @Override
    @Transactional
    public Product save(Product p) {
        ProductJPA pj = new ProductJPA(p);
        em.persist(pj);
        em.flush();
        return pj.toModel();
    }

    @Override
    public List<Product> findAll() {
        try {
            Query query = em.createQuery("SELECT p FROM ProductJPA p");
            List<ProductJPA> pjl = (List<ProductJPA>) query.getResultList();
            List<Product> pl = new ArrayList<>();
            for (ProductJPA pj : pjl) {
                pl.add(pj.toModel());
            }
            return pl;
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }
}
