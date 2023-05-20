package com.example.demochauluc.service.auth.impl;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demochauluc.entities.auth.User;
import com.example.demochauluc.repository.auth.UserRepository;
import com.example.demochauluc.service.auth.UserService;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    
    @Autowired
    public void setEntityRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User updateUserDetails (User userModel) throws LoginException{
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new LoginException("Người dùng với email "+ userEmail +" không tồn tại"));
        user.setName(userModel.getName());
        return userRepository.save(user);
    }
    }
