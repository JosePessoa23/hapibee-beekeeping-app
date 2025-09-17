package com.isep.acme.repositories;

import com.isep.acme.model.Modelo.Apiario;
import com.isep.acme.model.Modelo.Colmeia;
import com.isep.acme.repositories.IRepos.IColmeiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class ColmeiaRepository implements IColmeiaRepository {


    @Autowired
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public Colmeia save(Colmeia colmeia) {
        em.persist(colmeia);
        em.flush();
        return colmeia;
    }

    @Override
    public Optional<Colmeia> findById(Long colmeiaId) {
        try {
            Query query = em.createQuery("SELECT p FROM Colmeia p WHERE p.colmeiaId=:colmeiaID");
            query.setParameter("colmeiaID", colmeiaId);
            Colmeia pj = (Colmeia) query.getSingleResult();
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
