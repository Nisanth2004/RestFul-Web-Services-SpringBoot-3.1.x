package com.nisanth.restfulwebservices.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails
{
    //timestamp
    //message
    private String message;
    private LocalDateTime timestamp;
    private String details;

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails(LocalDateTime timestamp,String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
