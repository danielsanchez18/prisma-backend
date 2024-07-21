package com.example.prisma_backend.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idMessage;

    @ManyToOne
    @JoinColumn(name = "id_sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "id_receiver")
    private User receiver;

    private String title;
    private String content;
    private Date sentDate;
    private boolean revised;
    private String messageType;


    public Message() { }

    public Message(UUID idMessage, User sender, User receiver, String title, String content, Date sentDate, boolean revised, String messageType) {
        this.idMessage = idMessage;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.sentDate = sentDate;
        this.revised = revised;
        this.messageType = messageType;
    }

    public UUID getIdMessage() { return idMessage; }

    public void setIdMessage(UUID idMessage) { this.idMessage = idMessage; }

    public User getSender() { return sender; }

    public void setSender(User sender) { this.sender = sender; }

    public User getReceiver() { return receiver; }

    public void setReceiver(User receiver) { this.receiver = receiver; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Date getSentDate() { return sentDate; }

    public void setSentDate(Date sentDate) { this.sentDate = sentDate; }

    public boolean isRevised() { return revised; }

    public void setRevised(boolean revised) { this.revised = revised; }

    public String getMessageType() { return messageType; }

    public void setMessageType(String messageType) { this.messageType = messageType; }

}
