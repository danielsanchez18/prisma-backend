package com.example.prisma_backend.impl;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.repository.UserRepository;
import com.example.prisma_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) throws ResourceFoundException {
        Optional<User> existingUserByDni = userRepository .findUserByDni(user.getDni());

        if (existingUserByDni.isPresent()) {
            throw new ResourceFoundException("ERROR: User with Dni " + user.getDni() + " already exists");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> getUsersByFullName(String name, int page, int size) {
        return userRepository.findByName(name, page, size);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User getUserByDni(String dni) {
        return userRepository.findUserByDni(dni).orElseThrow();
    }

    @Override
    public User updateUser(UUID id, User updatedUser) throws ResourceFoundException {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            if (!existingUser.getDni().equals(updatedUser.getDni())) {
                Optional<User> userWithSameDni = userRepository.findUserByDni(updatedUser.getDni());
                if (userWithSameDni.isPresent()) {
                    throw new ResourceFoundException("El DNI ya está en uso por otro usuario");
                }
            }

            existingUser.setName(updatedUser.getName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setType(updatedUser.getType());
            existingUser.setDni(updatedUser.getDni());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setBirthDate(updatedUser.getBirthDate());
            existingUser.setActive(updatedUser.isActive());

            return userRepository.save(existingUser);
        } else {
            throw new ResourceFoundException("Usuario no encontrado");
        }
    }


    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
