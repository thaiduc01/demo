package com.example.firstproject2.mapper.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.firstproject2.dtos.UserDto;
import com.example.firstproject2.entities.UserEntity;
import com.example.firstproject2.mapper.UserMapper;

@Component
public class UserMapperImpl implements UserMapper{

    
    @Override
    public UserDto toDto(UserEntity userEntity) {
        if(userEntity == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
         
        return userDto;
    }
    
    @Override
    public List<UserDto> toDto(List<UserEntity> userEntity){
        List<UserDto> userDto = new ArrayList<>();
        for(UserEntity entity : userEntity) {
            userDto.add(toDto(entity));
        }
        return userDto;
    }
    
    @Override
    public UserEntity toEntity(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
       UserEntity userEntity = new UserEntity();
       userEntity.setId(userDto.getId());
       userEntity.setName(userDto.getName());
       userEntity.setEmail(userDto.getEmail());
        
        return userEntity;
    }
    
    @Override
    public UserEntity updateEntity(UserDto userDto, UserEntity userEntity) {
        if(userDto == null || userEntity == null) {
            return null;
        }
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }
}
