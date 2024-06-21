package com.prisma.prisma_backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistration;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private Date registrationDate;
    private String status;

    public Registration() { }

    public Registration(Long idRegistration, User user, Date registrationDate, String status) {
        this.idRegistration = idRegistration;
        this.user = user;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public Long getIdRegistration() { return idRegistration; }

    public void setIdRegistration(Long idRegistration) { this.idRegistration = idRegistration; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Date getRegistrationDate() { return registrationDate; }

    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

}
