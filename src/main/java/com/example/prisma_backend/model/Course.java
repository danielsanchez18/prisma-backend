package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCourse;

    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Class> classes;

    public Course() { }

    public Course(UUID idCourse, String name, List<Class> classes) {
        this.idCourse = idCourse;
        this.name = name;
        this.classes = classes;
    }

    public UUID getIdCourse() { return idCourse; }

    public void setIdCourse(UUID idCourse) { this.idCourse = idCourse; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Class> getClasses() { return classes; }

    public void setClasses(List<Class> classes) { this.classes = classes; }

}