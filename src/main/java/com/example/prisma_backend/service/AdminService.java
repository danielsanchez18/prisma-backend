package com.example.prisma_backend.service;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Admin;
import com.example.prisma_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    Admin addAdmin(Admin admin) throws ResourceFoundException;

    Admin getAdminById(String id);

    List<Admin> searchAdminsByFullName(String name, int page, int size);

    Page<Admin> getAllAdmins(Pageable pageable);

    Admin getAdminByDni(String dni);

    Admin updateAdmin(UUID id, Admin admin) throws ResourceFoundException;

}
