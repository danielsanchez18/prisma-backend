package com.prisma.prisma_backend.controller;

import com.prisma.prisma_backend.impl.UserServiceImpl;
import com.prisma.prisma_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
    }
}
