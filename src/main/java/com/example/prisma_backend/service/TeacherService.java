package com.example.prisma_backend.service;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Teacher;
import com.example.prisma_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher) throws ResourceFoundException;

    Teacher getTeacherById(String id);

    Page<Teacher> getAllTeachers(Pageable pageable);

    List<Teacher> searchTeacherByFullName(String name, int page, int size);

    Teacher getTeacherByDni(String dni);

    Teacher updateTeacher(UUID id, Teacher teacher) throws ResourceFoundException;
}
