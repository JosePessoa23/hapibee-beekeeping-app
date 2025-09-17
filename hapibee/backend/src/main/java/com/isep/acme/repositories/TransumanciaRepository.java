package com.isep.acme.repositories;

import com.isep.acme.model.Modelo.Transumancia;
import com.isep.acme.model.Modelo.TransumanciaDTO;
import com.isep.acme.model.User;
import com.isep.acme.model.UserView;
import com.isep.acme.repositories.IRepos.ITransumanciaRepository;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class TransumanciaRepository implements ITransumanciaRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;
    @Transactional
    @Override
    public Transumancia save(Transumancia transumancia) {
        em.persist(transumancia);
        em.flush();
        return transumancia;
    }

    @Override
    @Transactional
    public TransumanciaDTO update(String apiarioId) {
        Transumancia p = em.find(Transumancia.class,Long.parseLong(apiarioId));
        p.setApproved();
        return em.merge(p).toDTO();
    }
}
