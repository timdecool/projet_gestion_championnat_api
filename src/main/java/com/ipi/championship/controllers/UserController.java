package com.ipi.championship.controllers;

import com.ipi.championship.config.Security;
import com.ipi.championship.models.User;
import com.ipi.championship.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserRepository userRepository;
    PasswordEncoder encoder;

    public UserController(UserRepository userRepository, PasswordEncoder encoder)
    {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping
    public List<User> findAll()
    {
        return userRepository.findAll();
    }


    @GetMapping("/{id}")
    public User findById(@PathVariable(name = "id", required= false) User user)
    {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return user;
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable(name = "email", required= false) String email)
    {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @Valid @RequestBody User user,
            @PathVariable(name = "id", required = false) User userToUpdate
    )
    {
        if (userToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        user.setId(userToUpdate.getId());

        if(!encoder.matches(user.getPassword(), userToUpdate.getPassword())) {
            user.setPassword(encoder.encode(user.getPassword()));
        } else {
            user.setPassword(userToUpdate.getPassword());
        }
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id", required = false) User user)
    {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.delete(user);
    }
}
