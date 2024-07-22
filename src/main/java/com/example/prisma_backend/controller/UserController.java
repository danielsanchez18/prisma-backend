package com.example.prisma_backend.controller;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> createdUser(@RequestBody User user) throws ResourceFoundException {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/all")
    public Page<User> getAllUsers(@PageableDefault(page = 0, size = 10) Pageable pageable){
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/name/{name}")
    public List<User> getUsersByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return userService.getUsersByFullName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/dni/{dni}")
    public User getUserByDni(@PathVariable String dni) {
        return userService.getUserByDni(dni);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatedUser(@PathVariable UUID id, @RequestBody User user) throws ResourceFoundException {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
