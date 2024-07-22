package com.example.prisma_backend.controller;

import com.example.prisma_backend.exception.ResourceFoundException;
import com.example.prisma_backend.model.Teacher;
import com.example.prisma_backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teacher")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody Teacher teacher) throws ResourceFoundException {
        Teacher newTeacher = teacherService.addTeacher(teacher);
        return ResponseEntity.ok(newTeacher);
    }

    @GetMapping("/id/{id}")
    public Teacher getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/all")
    public Page<Teacher> getAllTeachers(@PageableDefault(page = 0, size = 10)Pageable pageable) {
        return teacherService.getAllTeachers(pageable);
    }

    @GetMapping("/name/{name}")
    public List<Teacher> getTeachersByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return teacherService.searchTeacherByFullName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/dni/{dni}")
    public Teacher getTeacherByDni(@PathVariable String dni) {
        return teacherService.getTeacherByDni(dni);
    }

    @PutMapping("/update/{id}")
    public Teacher updateTeacher(@PathVariable UUID id, @RequestBody Teacher teacher) throws ResourceFoundException {
        return teacherService.updateTeacher(id, teacher);
    }
}
