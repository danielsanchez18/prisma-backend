package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idSpeciality;

    private String nameSpeciality;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    public Speciality() { }

    public Speciality(UUID idSpeciality, String nameSpeciality, List<Teacher> teachers) {
        this.idSpeciality = idSpeciality;
        this.nameSpeciality = nameSpeciality;
        this.teachers = teachers;
    }

    public UUID getIdSpeciality() { return idSpeciality; }

    public void setIdSpeciality(UUID idSpeciality) { this.idSpeciality = idSpeciality; }

    public String getNameSpeciality() { return nameSpeciality; }

    public void setNameSpeciality(String nameSpeciality) { this.nameSpeciality = nameSpeciality; }

    public List<Teacher> getTeachers() { return teachers; }

    public void setTeachers(List<Teacher> teachers) { this.teachers = teachers; }

}
