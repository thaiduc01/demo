package com.example.firstproject2.service;

import java.util.List;

import com.example.firstproject2.dtos.UserDto;
import com.example.firstproject2.entities.UserEntity;

public interface UserService {

    List<UserDto> getAllUser();
    
    UserDto getUserById(Long id);
    
    UserDto  createNewUser(UserDto userDto);
    
    boolean updateUser(UserDto userDto);
    
    boolean deleteUser(Long id);
}
