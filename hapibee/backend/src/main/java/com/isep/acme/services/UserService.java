package com.isep.acme.services;

import com.isep.acme.model.*;
import com.isep.acme.repositories.IRepos.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isep.acme.repositories.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final IUserRepository userRepo;
    @Autowired
    private final UserViewMapper userViewMapper;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username - %s, not found", username)));
    }

    public UserView getUser(final String userId){
        return userViewMapper.toUserView(userRepo.findById(userId).get());
    }

    public Optional<User> getUserId(String user) {
        return userRepo.findById(user);
    }

    public Optional<User> getUserByUsername(final String username){
        return Optional.of(userRepo.findByUsername(username).get());
    }

    public UserView save(User user) {
        return userViewMapper.toUserView(userRepo.save(user));
    }

    public UserView updateByUserId(UserView user) {
        User newUser = userRepo.updateByUserId(user);
        return userViewMapper.toUserView(newUser);
    }
}
