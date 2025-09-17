package com.isep.acme.repositories;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.model.Modelo.exceptions.LatitudeInvalidaException;
import com.isep.acme.model.Product;
import com.isep.acme.model.User;
import com.isep.acme.repositories.IRepos.IApiarioRepository;
import com.isep.acme.repositories.IRepos.IUserRepository;
import com.isep.acme.repositories.dataModels.JPA.ProductJPA;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApiarioRepository implements IApiarioRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public List<Apiario> findAll() {
        try {
            Query query = em.createQuery("SELECT p FROM Apiario p");
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

    @Transactional
    @Override
    public Optional<Apiario> findBySku(String sku) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public void delete(String sku) {
        Apiario p = findBySku(sku).get();
        em.remove(p);
    }

    @Transactional
    @Override
    public Apiario updateBySku(String sku, Apiario p) {
        return null;
    }

    @Transactional
    @Override
    public Apiario save(Apiario apiario) {
        em.persist(apiario);
        em.flush();
        return apiario;
    }

    @Override
    @Transactional
    public ApiarioDTO transumancia(String apiarioId,double latitude,double longitude) throws LatitudeInvalidaException {
        Apiario p = em.find(Apiario.class,Long.parseLong(apiarioId));
        Coordenadas c = new Coordenadas(latitude,longitude);
        p.setCoordenadas(c);
        return em.merge(p).toDTO();
    }

    @Transactional
    @Override
    public Optional<Apiario> findById(Long apiarioID) {
        try {
            Query query = em.createQuery("SELECT p FROM Apiario p WHERE p.apiarioId=:apiarioID");
            query.setParameter("apiarioID", apiarioID);
            Apiario pj = (Apiario) query.getSingleResult();
            if (pj == null) {
                return Optional.empty();
            } else {
                return Optional.of(pj);
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Apiario> findByUserId(String userId) {
        /*
        try {
            Query query = em.createQuery("SELECT p FROM Apiario p WHERE p.user.username=:userID");
            query.setParameter("userID", userId);
            Apiario pj = (Apiario) query.getSingleResult();
            if (pj == null) {
                return Optional.empty();
            } else {
                return Optional.of(pj);
            }
        }catch (NoResultException e){
            return Optional.empty();
        }*/
        return  null;
    }

    @Override
    public Optional<Apiario> findByColmeia(Long colmeiaID) {
        try {
            Query query = em.createQuery("SELECT a FROM Apiario a JOIN a.colmeiaList c WHERE c.colmeiaId = :colmeiaId");
            query.setParameter("colmeiaId", colmeiaID);
            Apiario pj = (Apiario) query.getSingleResult();
            if (pj == null) {
                return Optional.empty();
            } else {
                return Optional.of(pj);
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}
