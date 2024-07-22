package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idUser;

    private String type;
    private String dni;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private Date birthDate;
    private boolean active;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Admin admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Applicant applicant;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    public User() { }

    public User(UUID idUser, String type, String dni, String name, String lastName, String email, String phone, Date birthDate, boolean active) {
        this.idUser = idUser;
        this.type = type;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.active = active;
    }

    public UUID getIdUser() { return idUser; }

    public void setIdUser(UUID idUser) { this.idUser = idUser; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public boolean getActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public Admin getAdmin() { return admin; }

    public void setAdmin(Admin admin) { this.admin = admin; }

    public Student getStudent() { return student; }

    public void setStudent(Student student) { this.student = student; }

    public Teacher getTeacher() { return teacher; }

    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public Applicant getApplicant() { return applicant; }

    public void setApplicant(Applicant applicant) { this.applicant = applicant; }

    public List<Registration> getRegistrations() { return registrations; }

    public void setRegistrations(List<Registration> registrations) { this.registrations = registrations; }

    public List<Notification> getNotifications() { return notifications; }

    public void setNotifications(List<Notification> notifications) { this.notifications = notifications; }

    public boolean isActive() { return active; }

    public List<Message> getSentMessages() { return sentMessages; }

    public void setSentMessages(List<Message> sentMessages) { this.sentMessages = sentMessages; }

    public List<Message> getReceivedMessages() { return receivedMessages; }

    public void setReceivedMessages(List<Message> receivedMessages) { this.receivedMessages = receivedMessages; }

}