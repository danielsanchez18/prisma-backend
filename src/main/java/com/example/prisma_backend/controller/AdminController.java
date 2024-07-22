package com.example.prisma_backend.controller;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Admin;
import com.example.prisma_backend.model.User;
import com.example.prisma_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public ResponseEntity<Admin> createdAdmin(@RequestBody Admin admin) throws ResourceFoundException {
        Admin newAdmin = adminService.addAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @GetMapping("/id/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }

    @GetMapping("/name/{name}")
    public List<Admin> getAdminsByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return adminService.searchAdminsByFullName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/all")
    public Page<Admin> getAllAdmins(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return adminService.getAllAdmins(pageable);
    }

    @GetMapping("/dni/{dni}")
    public Admin getAdminByDni(@PathVariable String dni) {
        return adminService.getAdminByDni(dni);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updatedAdmin(@PathVariable UUID id, @RequestBody Admin admin) throws ResourceFoundException {
        return ResponseEntity.ok(adminService.updateAdmin(id, admin));
    }

}
