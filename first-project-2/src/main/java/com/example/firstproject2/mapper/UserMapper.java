package com.example.firstproject2.mapper;

import java.util.List;
import com.example.firstproject2.dtos.UserDto;
import com.example.firstproject2.entities.UserEntity;

// TODO use mapstruct and remove implementation package
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);
    
    List<UserDto> toDto(List<UserEntity> userEntity);

    UserEntity toEntity(UserDto userDto);

    UserEntity updateEntity(UserDto userDto, UserEntity userEntity);
}
