package com.example.prisma_backend.repository;

import com.example.prisma_backend.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "CALL sp_find_last_teacher_id()", nativeQuery = true)
    String findLastTeacherId();

    @Query(value = "CALL sp_find_teachers_by_full_name(:name, :page, :size)", nativeQuery = true)
    List<Teacher> findTeachersByFullName(@Param("name") String name, @Param("page") int page, @Param("size") int size);

    @Query(value = "CALL sp_find_teacher_by_dni(:dni)", nativeQuery = true)
    Teacher findTeacherByDni(@Param("dni") String dni);

}
