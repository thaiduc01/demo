package com.example.demochauluc.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.dtos.auth.AuthenticatedReponseDto;
import com.example.demochauluc.dtos.auth.LoginDto;
import com.example.demochauluc.dtos.auth.RegisterDto;
import com.example.demochauluc.service.auth.ManageUserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/manage")
public class ManageUserController {

    @Autowired
    private ManageUserService manage;
    
    
    private static String admin = "ROLE_ADMIN";
    private static String user = "ROLE_USER";
    
    @PostMapping("/register")
    @Operation(summary = "Đăng kí người dùng")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto dto){
        String msg = manage.registerCustom(dto, user);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Đăng nhập")
    public ResponseEntity<AuthenticatedReponseDto> loginUser(@Valid @RequestBody LoginDto loginDto){
        
        AuthenticatedReponseDto responseDto = manage.loginUser(loginDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    
    
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @PostMapping("/admin/register")
//    @Operation(summary = "Đăng kí Admin")
//    public ResponseEntity<String> registerAdminForSingleUserOnly(@Valid @RequestBody RegisterDto registerDto){
//        return new ResponseEntity<>(manage.registerCustom(registerDto, admin),HttpStatus.CREATED);
//    }
}
