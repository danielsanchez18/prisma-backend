package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "student_class_assignment")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAssignment;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_class", nullable = false)
    private Class class_;

    public StudentClass() { }

    public StudentClass(UUID idAssignment, Student student, Class class_) {
        this.idAssignment = idAssignment;
        this.student = student;
        this.class_ = class_;
    }

    public UUID getIdAssignment() { return idAssignment; }

    public void setIdAssignment(UUID idAssignment) { this.idAssignment = idAssignment; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Class getClass_() { return class_; }

    public void setClass_(Class class_) { this.class_ = class_; }

}
