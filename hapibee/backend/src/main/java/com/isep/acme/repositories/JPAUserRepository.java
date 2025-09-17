package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import com.isep.acme.model.User;
import com.isep.acme.model.UserView;
import com.isep.acme.repositories.IRepos.IUserRepository;
import com.isep.acme.repositories.dataModels.JPA.ProductJPA;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

public class JPAUserRepository implements IUserRepository {

    @Autowired
    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            Query query = em.createQuery("SELECT u FROM UserJPA u WHERE u.username=:username");
            query.setParameter("username", username);
            UserJPA uj = (UserJPA) query.getSingleResult();
            if (uj == null) {
                return Optional.empty();
            } else {
                return Optional.of(uj.toModel());
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserJPA> findByUsername2(String username) {
        try {
            Query query = em.createQuery("SELECT u FROM UserJPA u WHERE u.username=:username");
            query.setParameter("username", username);
            UserJPA uj = (UserJPA) query.getSingleResult();
            if (uj == null) {
                return Optional.empty();
            } else {
                return Optional.of(uj);
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(String userId) {
        try {
            Long id = Long.parseLong(userId);
            Query query = em.createQuery("SELECT u FROM UserJPA u WHERE u.userId=:id");
            query.setParameter("id", id);
            UserJPA uj = (UserJPA) query.getSingleResult();
            if (uj == null) {
                return Optional.empty();
            } else {
                return Optional.of(uj.toModel());
            }
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        UserJPA u = new UserJPA(user);
        em.persist(u);
        em.flush();
        return u.toModel();
    }

    @Override
    @Transactional
    public User updateByUserId(UserView user) {
        UserJPA p = em.find(UserJPA.class,Long.parseLong(user.getUserId()));
        p.setFullName(user.getFullName());
        p.setNif(user.getNif());
        p.setMorada(user.getMorada());
        p.setCodigo_Postal(user.getCodigo_Postal());
        p.setPhonenumber(Long.parseLong(user.getPhonenumber()));
        p.setNumero_Apicultor(user.getNumero_Apicultor());
        return em.merge(p).toModel();
    }
}
