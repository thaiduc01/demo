package com.example.firstproject2.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject2.dtos.UserDto;
import com.example.firstproject2.entities.UserEntity;
import com.example.firstproject2.mapper.UserMapper;
import com.example.firstproject2.repository.UserRepository;
import com.example.firstproject2.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;
    
    @Override
    public List<UserDto> getAllUser(){
        List<UserEntity> userEntity = repository.findAll();
        return mapper.toDto(userEntity);
    }
    
    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = repository.findById(id);
        return mapper.toDto(userEntity);
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        UserEntity newUser = mapper.toEntity(userDto);
        repository.save(newUser);
        return mapper.toDto(newUser);
    }
    
    @Override
    public boolean updateUser(UserDto dto) {
        Long userId = dto.getId();
        UserEntity user = repository.findById(userId);
        if(user == null) {
            return false;
        }
        user = mapper.updateEntity(dto, user);
        repository.save(user);
        return true;
    }
    
    @Override
    public boolean deleteUser(Long id) {
        UserEntity user = repository.findById(id);
        if(user == null) {
            return false;
        }
        
        repository.delete(user);
        
        return true;
    }
}
