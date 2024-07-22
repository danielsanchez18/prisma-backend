package com.example.prisma_backend.service;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Student;
import com.example.prisma_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    Student addStudent(Student student) throws ResourceFoundException;

    Student getStudentById(String id);

    List<Student> searchStudentsByFullName(String name, int page, int size);

    Page<Student> getAllStudents(Pageable pageable);

    Student getStudentByDni(String dni);

    Student updateStudent(UUID id, Student student) throws ResourceFoundException;

}
