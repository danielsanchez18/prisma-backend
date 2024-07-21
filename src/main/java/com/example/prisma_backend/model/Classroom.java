package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idClassroom;

    private String code;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Class> classes;

    public Classroom() { }

    public Classroom(UUID idClassroom, String code, List<Class> classes) {
        this.idClassroom = idClassroom;
        this.code = code;
        this.classes = classes;
    }

    public UUID getIdClassroom() { return idClassroom; }

    public void setIdClassroom(UUID idClassroom) { this.idClassroom = idClassroom; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public List<Class> getClasses() { return classes; }

    public void setClasses(List<Class> classes) { this.classes = classes; }

}
