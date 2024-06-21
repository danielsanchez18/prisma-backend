package com.prisma.prisma_backend.repository;

import com.prisma.prisma_backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query(value = "CALL sp_find_admin_by_full_name(:name)", nativeQuery = true)
    List<Admin> findAdminsByFullName (String name);

    @Query(value = "CALL sp_find_last_admin_id()", nativeQuery = true)
    String findLastAdminId();

}
