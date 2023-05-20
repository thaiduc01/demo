package com.example.demochauluc.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedReponseDto {

    private final String token;
    
    private final String tokenType = "Bearer ";
    
    public AuthenticatedReponseDto(String token) {
        this.token = token;
        
    }
}
