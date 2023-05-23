package com.example.demochauluc.service.auth.impl;

import java.util.Collections;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demochauluc.Exception.UserAlreadyExsistException;
import com.example.demochauluc.Exception.UserDoesNotExtistException;
import com.example.demochauluc.config.TokenGenerator;
import com.example.demochauluc.dtos.auth.AuthenticatedReponseDto;
import com.example.demochauluc.dtos.auth.LoginDto;
import com.example.demochauluc.dtos.auth.RegisterDto;
import com.example.demochauluc.entities.auth.Role;
import com.example.demochauluc.entities.auth.User;
import com.example.demochauluc.repository.auth.RoleEntityRepository;
import com.example.demochauluc.repository.auth.UserRepository;
import com.example.demochauluc.service.auth.ManageUserService;

@Service
public class ManageUserServiceImpl implements ManageUserService{

    @Autowired
    public AuthenticationManager authenticationManager;
    
    @Autowired
    public PasswordEncoder encoder;
    
    @Autowired
    public RoleEntityRepository roleRepository;
    
    @Autowired
    public TokenGenerator generator;
    
    @Autowired
    public UserRepository userRepository;
    
    @Override
    public String registerCustom(RegisterDto dto,String userType) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExsistException("Người dùng đã tồn tại");
        }
            User user = new User();
            
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setMobileNumber(dto.getMobileNo());
            user.setPassword(dto.getPassword());
            
            Role role = roleRepository.findRoleByRole(userType).get();
            
            user.setRoles(Collections.singletonList(role));
            
            userRepository.save(user);
            return "Bạn đã đăng ký thành công";
    }
    
    @Override
    public AuthenticatedReponseDto loginUser(@NotNull LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        return new AuthenticatedReponseDto(token);
    }
    
    @Override
    public User getUser() {
        try {
            Object o = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            UserDetails userDetails = (UserDetails) o;
            String username = userDetails.getUsername();
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UserDoesNotExtistException("Người dùng không tồn tại"));
        }catch (Exception e) {
            throw new RuntimeException("Vui lòng kiểm tra lại mã thông báo");
        }
    }
}
