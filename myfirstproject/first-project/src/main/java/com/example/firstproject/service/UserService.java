package com.example.firstproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.firstproject.entity.UserEntity;
import com.example.firstproject.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public final int pagingNumber = 2;
    
    public void save(UserEntity users) {
        userRepository.save(users);
    }
    
    public java.util.List<UserEntity> getAllUser(){
        return userRepository.getAllUser();
    }
    
    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }
    
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<UserEntity> findUserByKey(String keyword){
        return userRepository.findUserByKeyword(keyword);
    }
    
    public Page<UserEntity> getUserByPage(int Page){
        return userRepository.findAll(PageRequest.of(Page-1, pagingNumber));
    }
   
}
