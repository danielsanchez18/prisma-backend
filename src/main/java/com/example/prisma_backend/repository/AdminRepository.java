package com.example.prisma_backend.repository;

import com.example.prisma_backend.model.Admin;
import com.example.prisma_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "CALL sp_find_last_admin_id()", nativeQuery = true)
    String findLastAdminId();

    @Query(value = "CALL sp_find_admins_by_full_name(:name, :page, :size)", nativeQuery = true)
    List<Admin> findAdminsByFullName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_admin_by_dni(:dni)", nativeQuery = true)
    Admin findAdminByDni(@Param("dni") String dni);

}