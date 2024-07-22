package com.example.prisma_backend.repository;

import com.example.prisma_backend.model.Student;
import com.example.prisma_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "CALL sp_find_last_student_id()", nativeQuery = true)
    String findLastStudentId();

    @Query(value = "CALL sp_find_students_by_full_name(:name, :page, :size)", nativeQuery = true)
    List<Student> findStudentsByFullName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_student_by_dni(:dni)", nativeQuery = true)
    Student findStudentByDni(@Param("dni") String dni);

}