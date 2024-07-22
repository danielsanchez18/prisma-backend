package com.example.prisma_backend.controller;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Student;
import com.example.prisma_backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) throws ResourceFoundException {
        return studentService.addStudent(student);
    }

    @GetMapping("/all")
    public Page<Student> getAllStudents(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/id/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/dni/{dni}")
    public Student getStudentByDni(@PathVariable String dni) {
        return studentService.getStudentByDni(dni);
    }

    @GetMapping("/name/{name}")
    public List<Student> searchStudentsByFullName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return studentService.searchStudentsByFullName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable UUID id, @RequestBody Student student) throws ResourceFoundException {
        return studentService.updateStudent(id, student);
    }
}
