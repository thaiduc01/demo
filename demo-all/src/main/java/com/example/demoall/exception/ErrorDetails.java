package com.example.demoall.exception;

import java.util.List;

import lombok.Data;

@Data
public class ErrorDetails {

    private final String message;
    
    private final long timestamp;
    
    private List<String> errorDetail;
    
    public ErrorDetails(final String message) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }
}
