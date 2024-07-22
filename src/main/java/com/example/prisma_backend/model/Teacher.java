package com.example.prisma_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(length = 10)
    private String idTeacher;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_speciality")
    private Speciality speciality;

    private LocalDate entryDate;
    private String password;
    private String profile;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Class> classes;

    public Teacher() { }

    public Teacher(String idTeacher, User user, Speciality speciality, LocalDate entryDate, String password, String profile, List<Class> classes) {
        this.idTeacher = idTeacher;
        this.user = user;
        this.speciality = speciality;
        this.entryDate = entryDate;
        this.password = password;
        this.profile = profile;
        this.classes = classes;
    }

    public String getIdTeacher() { return idTeacher; }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Speciality getSpeciality() { return speciality; }

    public void setSpeciality(Speciality speciality) { this.speciality = speciality; }

    public LocalDate getEntryDate() { return entryDate; }

    public void setEntryDate(LocalDate entryDate) { this.entryDate = entryDate; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getProfile() { return profile; }

    public void setProfile(String profile) { this.profile = profile; }

    public List<Class> getClasses() { return classes; }

    public void setClasses(List<Class> classes) { this.classes = classes; }

}
