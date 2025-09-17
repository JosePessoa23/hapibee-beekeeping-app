package com.isep.acme.controllers;

import com.isep.acme.model.Product;
import com.isep.acme.model.ProductDTO;
import com.isep.acme.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.isep.acme.model.UserView;
import com.isep.acme.services.UserService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/admin/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserView getUser(@PathVariable final String userId) {

        return userService.getUser(userId);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable final String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        return ResponseEntity.ok().body(userDetails);
    }

    @PostMapping
    public ResponseEntity<UserView> create(@RequestBody User user) {
        UserView userView = userService.save(user);

        return ResponseEntity.ok().body(userView);
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserView> Update(@PathVariable final String userId,@RequestBody final UserView user) {
        user.setUserId(userId);
        UserView userView = userService.updateByUserId(user);
        return ResponseEntity.ok().body(userView);
    }
}
