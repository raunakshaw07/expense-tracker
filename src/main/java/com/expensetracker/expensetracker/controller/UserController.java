package com.expensetracker.expensetracker.controller;

import com.expensetracker.expensetracker.model.User;
import com.expensetracker.expensetracker.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("/login")
    public ResponseEntity<String> Login() {
        List<User> users = userDetailsService.getAllUsers();
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDetailsService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable UUID id) {
        Optional<User> User = userDetailsService.getSingleUser(id);
        return User.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User User) {
        User newUser = userDetailsService.saveUser(User);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User User) {
        if (!userDetailsService.getSingleUser(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User.setId(id);
        User updatedUser = userDetailsService.saveUser(User);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (!userDetailsService.getSingleUser(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userDetailsService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
    
}
