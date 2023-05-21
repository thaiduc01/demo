package com.example.demochauluc.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ErrorDetails {

    private final String message;
    
    private final long timestamp;
    
    private String fieldName;
    
    @JsonIgnoreProperties(value = "childLevel")
    private Collection<ErrorDetails> childLevel;
    
    private List<String> errorDetail;



    public ErrorDetails(final String message, String fieldName, Collection<ErrorDetails> childLevel) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        this.fieldName = fieldName;
        this.childLevel = childLevel;
    }

    public ErrorDetails(final String message,final String fieldName) {
        this(message,fieldName, new ArrayList<>());
     }


    public ErrorDetails(final String message) {
       this(message,null, new ArrayList<>());
    }
}
