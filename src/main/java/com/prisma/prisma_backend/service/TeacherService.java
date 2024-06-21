package com.prisma.prisma_backend.service;

import com.prisma.prisma_backend.model.Teacher;
import com.prisma.prisma_backend.model.User;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);

    Teacher getTeacherById(String id);

    List<Teacher> getAllTeachers();

    List<Teacher> getTeachersByFullName(String name);

    Optional<Teacher> getTeacherByDni (String dni);

    Teacher updateTeacher(Long id, Teacher teacher);

}
