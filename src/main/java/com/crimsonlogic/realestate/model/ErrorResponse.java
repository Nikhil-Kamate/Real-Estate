package com.crimsonlogic.realestate.model;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private int status;
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorResponse(int status, LocalDateTime timestamp, String message, String details) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
