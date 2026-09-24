package com.bit.backend.dtos;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class NotificationDto {

    private Integer id;
    private Integer recipientId;
    private String recipientType;
    private String message;
    private String type;
    private LocalDateTime sentDate;
    private Boolean readStatus;

    public NotificationDto() {
    }

    public NotificationDto(Integer id, Integer recipientId, String recipientType, String message, String type, LocalDateTime sentDate, Boolean readStatus) {
        this.id = id;
        this.recipientId = recipientId;
        this.recipientType = recipientType;
        this.message = message;
        this.type = type;
        this.sentDate = sentDate;
        this.readStatus = readStatus;
    }

    public Integer getId() {
        return id;
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
