package com.example.demochauluc.exception;

public class UserDoesNotExtistException extends RuntimeException{

    public UserDoesNotExtistException(String mssg) {
        super(mssg);
    }
}
