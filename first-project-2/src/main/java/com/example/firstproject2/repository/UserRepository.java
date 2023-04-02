package com.example.firstproject2.repository;

import java.util.List;

import com.example.firstproject2.entities.UserEntity;

// TODO extends JPARepository and remove implementation package
public interface UserRepository {

    List<UserEntity> findAll();
    
    UserEntity findById(Long id);
    
    void save(UserEntity userEntity);
    
    void delete(UserEntity userEntity);
    
    
}
