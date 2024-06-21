package com.prisma.prisma_backend.impl;

import com.prisma.prisma_backend.model.Admin;
import com.prisma.prisma_backend.model.User;
import com.prisma.prisma_backend.repository.AdminRepository;
import com.prisma.prisma_backend.repository.UserRepository;
import com.prisma.prisma_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Admin addAdmin(Admin admin) {
        User user = admin.getUser();
        admin.setIdAmin(generateAdminId());
        user.setType("ADMIN");
        user.setActive(true);
        user = userServiceImpl.saveUser(user);
        admin.setUser(user);
        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public List<Admin> getAdminsByFullName(String name) {
        return adminRepository.findAdminsByFullName(name);
    }

    @Override
    public Admin updateAdmin(Long id, Admin admin) {
        Optional<User> existingAdmin = userRepository.findById(id);
        if (existingAdmin.isPresent()) {
            Admin admin1 = existingAdmin.get().getAdmin();
            admin1.getUser().setName(admin.getUser().getName());
            admin1.getUser().setLastName(admin.getUser().getLastName());
            admin1.getUser().setEmail(admin.getUser().getEmail());
            admin1.getUser().setPhone(admin.getUser().getPhone());
            admin1.setProfile(admin1.getProfile());

            return adminRepository.save(admin1);
        }
        return null;
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
