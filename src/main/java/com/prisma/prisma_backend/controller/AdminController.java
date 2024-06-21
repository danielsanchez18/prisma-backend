package com.prisma.prisma_backend.controller;

import com.prisma.prisma_backend.impl.AdminServiceImpl;
import com.prisma.prisma_backend.model.Admin;
import com.prisma.prisma_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @PostMapping("/save")
    public ResponseEntity<Admin> createdAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminServiceImpl.addAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminServiceImpl.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/name/{name}")
    public List<Admin> getAdminsByName(@PathVariable String name) {
        return adminServiceImpl.getAdminsByFullName(name);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updatedAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(adminServiceImpl.updateAdmin(id, admin));
    }
}
