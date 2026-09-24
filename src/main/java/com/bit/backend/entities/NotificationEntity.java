package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recipient_id")
    private Integer recipientId;

    @Column(name = "recipient_type")
    private String recipientType;

    @Column(name = "message")
    private String message;

    @Column(name = "type")
    private String type;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "read_status")
    private Boolean readStatus;

    public NotificationEntity() {
    }

    public NotificationEntity(Integer id, Integer recipientId, String recipientType, String message, String type, LocalDateTime sentDate, Boolean readStatus) {
        this.id = id;
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.message = message;
        this.type = type;
        this.sentDate = sentDate;
        this.readStatus = readStatus;
    }

    public Integer getId() {        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Boolean readStatus) {
        this.readStatus = readStatus;
    }
}
