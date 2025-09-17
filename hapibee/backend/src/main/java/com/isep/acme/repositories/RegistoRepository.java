package com.isep.acme.repositories;

import com.isep.acme.model.Modelo.Inspeção;
import com.isep.acme.repositories.IRepos.IRegistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RegistoRepository implements IRegistoRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Inspeção> findAllByApicultorId(String apicultorId) {
        List<Inspeção> registoList;
        try {
            Query query = em.createQuery("SELECT p FROM Inspeção p WHERE p.apicultorId=:apiarioID");
            query.setParameter("apiarioID", apicultorId);
            registoList = (List<Inspeção>) query.getResultList();
            if (registoList == null) {
                return registoList;
            } else {
                return registoList;
            }
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional
    public Inspeção save(Inspeção inspeção) {
        em.persist(inspeção);
        em.flush();
        return inspeção;
    }
}
