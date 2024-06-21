package com.prisma.prisma_backend.service;

import com.prisma.prisma_backend.model.Admin;
import com.prisma.prisma_backend.model.Student;
import com.prisma.prisma_backend.model.User;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(Student student);

    Student getStudentById(String id);

    List<Student> getAllStudents();

    List<Student> getStudentsByFullName(String name);

    Optional<User> getStudentByDni(String dni);

    Student updateStudent(Long id, Student student);

}
