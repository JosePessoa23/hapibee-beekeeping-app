package com.isep.acme.repositories.IRepos;

import com.isep.acme.model.User;
import com.isep.acme.model.UserView;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;

import java.util.Optional;

public interface IUserRepository {

    User save(User entity);

    Optional<User> findById(String userId);

    Optional<User> findByUsername(String username);

    Optional<UserJPA> findByUsername2(String username);

    User updateByUserId(UserView user);
}
