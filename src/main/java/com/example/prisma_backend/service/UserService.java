package com.example.prisma_backend.service;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User saveUser(User user) throws ResourceFoundException;

    Page<User> getAllUsers(Pageable pageable);

    List<User> getUsersByFullName(String name, int page, int size);

    User getUserById(UUID id);

    User getUserByDni(String dni);

    User updateUser(UUID id, User user) throws ResourceFoundException;

    void deleteUser(UUID id);
}
