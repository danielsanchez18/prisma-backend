package com.example.prisma_backend.impl;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Admin;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.repository.AdminRepository;
import com.example.prisma_backend.repository.UserRepository;
import com.example.prisma_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Admin addAdmin(Admin admin) throws ResourceFoundException {
        User user = admin.getUser();
        admin.setIdAmin(generateAdminId());
        user.setType("ADMIN");
        user.setActive(true);
        user = userServiceImpl.saveUser(user);
        admin.setUser(user);
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(String id) {
        return adminRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Admin> searchAdminsByFullName(String name, int page, int size) {
        return adminRepository.findAdminsByFullName(name, page, size);
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Admin getAdminByDni(String dni) {
        return adminRepository.findAdminByDni(dni);
    }

    @Override
    public Admin updateAdmin(UUID id, Admin updatedAdmin) throws ResourceFoundException {
        Optional<User> existingAdminOptional = userRepository.findById(id);
        if (existingAdminOptional.isPresent()) {
            User existingUser = existingAdminOptional.get();
            Admin admin = existingUser.getAdmin();

            if (!existingUser.getDni().equals(updatedAdmin.getUser().getDni())) {
                Optional<User> userWithSameDni = userRepository.findUserByDni(updatedAdmin.getUser().getDni());
                if (userWithSameDni.isPresent()) {
                    throw new ResourceFoundException("El DNI ya está en uso por otro usuario");
                }
            }

            existingUser.setName(updatedAdmin.getUser().getName());
            existingUser.setLastName(updatedAdmin.getUser().getLastName());
            existingUser.setEmail(updatedAdmin.getUser().getEmail());
            existingUser.setPhone(updatedAdmin.getUser().getPhone());
            existingUser.setDni(updatedAdmin.getUser().getDni());
            existingUser.setActive(updatedAdmin.getUser().isActive());

            admin.setProfile(updatedAdmin.getProfile());

            userRepository.save(existingUser);

            return adminRepository.save(admin);
        } else {
            throw new ResourceFoundException("Usuario no encontrado");
        }
    }

    private String generateAdminId() {
        String lastAdminId = adminRepository.findLastAdminId();
        if (lastAdminId == null) {
            return "A240000001";
        }

        String sequence = lastAdminId.substring(3);
        int sequenceNumber = Integer.parseInt(sequence);
        sequenceNumber++;

        return "A24" + String.format("%07d", sequenceNumber);
    }
}
