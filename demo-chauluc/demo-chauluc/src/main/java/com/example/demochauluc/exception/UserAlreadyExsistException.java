package com.example.demochauluc.Exception;

public class UserAlreadyExsistException extends RuntimeException{

    public UserAlreadyExsistException(String mssg) {
        super(mssg);
    }
}
