package com.example.demochauluc.Service.auth;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demochauluc.Exception.UserDoesNotExtistException;
import com.example.demochauluc.config.TokenGenerator;
import com.example.demochauluc.entities.auth.Role;
import com.example.demochauluc.repository.auth.UserRepository;

@Service
public class ApplicationUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private TokenGenerator generator;
    
    @Override 
    public UserDetails loadUserByUsername(String username) {
        com.example.demochauluc.entities.auth.User user = repository.findByEmail(username)
                .orElseThrow(()->
                new  UserDoesNotExtistException("Người dùng không tồn tại ngoại lệ"));
        
        return new User(user.getEmail(),
                user.getPassword(),
                mapRoleToUser(user.getRoles()));
    }
    
    
    private Collection<GrantedAuthority> mapRoleToUser(List<Role> roleList){
        return roleList
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet());
    }
}
