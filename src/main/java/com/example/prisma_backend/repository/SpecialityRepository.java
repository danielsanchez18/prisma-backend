package com.example.prisma_backend.repository;

import com.example.prisma_backend.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

    @Query(value = "CALL sp_find_speciality_by_name(:name)", nativeQuery = true)
    Speciality findSpecialityByName(@Param("name") String name);

    @Query(value = "CALL sp_search_speciality_by_name(:name, :page, :size)", nativeQuery = true)
    List<Speciality> searchSpecialityByName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

}
