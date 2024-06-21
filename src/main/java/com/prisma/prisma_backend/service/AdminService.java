package com.prisma.prisma_backend.service;

import com.prisma.prisma_backend.model.Admin;

import java.util.List;

public interface AdminService {

    Admin addAdmin(Admin admin);

    List<Admin> getAllAdmins();

    List<Admin> getAdminsByFullName(String name);

    Admin updateAdmin(Long id, Admin admin);

}
