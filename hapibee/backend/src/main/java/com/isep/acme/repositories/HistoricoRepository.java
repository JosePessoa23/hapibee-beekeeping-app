package com.isep.acme.repositories;

import com.isep.acme.model.Modelo.Apiario;
import com.isep.acme.model.Modelo.Historico;
import com.isep.acme.repositories.IRepos.IHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class HistoricoRepository implements IHistoricoRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;


    @Transactional
    @Override
    public Historico save(Historico historico) {
        em.persist(historico);
        em.flush();
        return historico;
    }


    @Override
    public List<Historico> getByApiarioId(Long apiarioId) {
        try {
            Query query = em.createQuery("SELECT a FROM Historico a WHERE a.apiarioId = :apiarioId");
            query.setParameter("apiarioId", apiarioId);
            return query.getResultList();

        }catch (NoResultException e){
            return null;
        }
    }
}
