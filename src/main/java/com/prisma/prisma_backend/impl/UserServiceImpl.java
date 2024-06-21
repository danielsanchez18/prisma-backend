package com.prisma.prisma_backend.impl;

import com.prisma.prisma_backend.model.User;
import com.prisma.prisma_backend.repository.UserRepository;
import com.prisma.prisma_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        Optional<User> existingUserByDni = userRepository.findByDni(user.getDni());
        if (existingUserByDni.isPresent()) {
            throw new IllegalArgumentException("ERROR: User with Dni " + user.getDni() + " already exists");
        }

        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("ERROR: User with Email " + user.getEmail() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
