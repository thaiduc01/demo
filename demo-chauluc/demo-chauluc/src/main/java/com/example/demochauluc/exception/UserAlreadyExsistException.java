package com.example.demochauluc.exception;

public class UserAlreadyExsistException extends RuntimeException{

    public UserAlreadyExsistException(String mssg) {
        super(mssg);
    }
}
