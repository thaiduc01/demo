package com.example.firstproject2.repository;

import java.util.List;

import com.example.firstproject2.entities.UserEntity;

public interface UserRepository {

    List<UserEntity> findAll();
    
    UserEntity findById(Long id);
    
    void save(UserEntity userEntity);
    
    void delete(UserEntity userEntity);
    
    
}
