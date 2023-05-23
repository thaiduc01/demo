package com.example.demochauluc.Exception;

public class UserDoesNotExtistException extends RuntimeException{

    public UserDoesNotExtistException(String mssg) {
        super(mssg);
    }
}
