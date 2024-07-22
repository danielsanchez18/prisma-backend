package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idNotification;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    private String title;
    private String message;
    private LocalDate creationDate;
    private boolean revised;

    public Notification() { }

    public Notification(UUID idNotification, User user, String title, String message, LocalDate creationDate, boolean revised) {
        this.idNotification = idNotification;
        this.user = user;
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.revised = revised;
    }

    public UUID getIdNotification() { return idNotification; }

    public void setIdNotification(UUID idNotification) { this.idNotification = idNotification; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public LocalDate getCreationDate() { return creationDate; }

    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public boolean isRevised() { return revised; }

    public void setRevised(boolean revised) { this.revised = revised; }

}
