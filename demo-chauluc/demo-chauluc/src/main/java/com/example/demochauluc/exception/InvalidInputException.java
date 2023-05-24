package com.example.demochauluc.exception;

import java.util.Map;

import org.springframework.validation.BindingResult;

public class InvalidInputException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private BindingResult result;
    
    public InvalidInputException(BindingResult result) {
        this.result = result;
    }
    
    public BindingResult getResult() {
        return result;
    }
    
    private Map<String, String> errors;
    
    public InvalidInputException(String message,Map<String,String>errors) {
        super(message);
        this.errors = errors;
    }
    
    public Map<String, String> getErrors(){
        return errors;
    }
}
