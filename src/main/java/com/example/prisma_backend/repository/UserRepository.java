package com.example.prisma_backend.repository;

import com.example.prisma_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "CALL sp_find_user_by_dni(:dni)", nativeQuery = true)
    Optional<User> findUserByDni(@Param("dni") String dni);

    @Query(value = "CALL sp_find_user_by_name(:name, :page, :size)", nativeQuery = true)
    List<User> findByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);
}
