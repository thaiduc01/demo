package com.example.demochauluc.Service.auth;

import com.example.demochauluc.dtos.auth.AuthenticatedReponseDto;
import com.example.demochauluc.dtos.auth.LoginDto;
import com.example.demochauluc.dtos.auth.RegisterDto;
import com.example.demochauluc.entities.auth.User;

public interface ManageUserService {

    String registerCustom(RegisterDto dto,String userType);
    
    AuthenticatedReponseDto loginUser(LoginDto loginDto);
    
    User getUser();
}
