package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "qualification")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idQualification;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_class")
    private Class class_;

    private int bimester;
    private int value;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Class course;

    public Qualification() { }

    public Qualification(UUID idQualification, Student student, Class class_, int bimester, int value, Class course) {
        this.idQualification = idQualification;
        this.student = student;
        this.class_ = class_;
        this.bimester = bimester;
        this.value = value;
        this.course = course;
    }

    public UUID getIdQualification() { return idQualification; }

    public void setIdQualification(UUID idQualification) { this.idQualification = idQualification; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Class getClass_() { return class_; }

    public void setClass_(Class class_) { this.class_ = class_; }

    public int getBimester() { return bimester; }

    public void setBimester(int bimester) { this.bimester = bimester; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public Class getCourse() {
        return course;
    }

    public void setCourse(Class course) {
        this.course = course;
    }
}
