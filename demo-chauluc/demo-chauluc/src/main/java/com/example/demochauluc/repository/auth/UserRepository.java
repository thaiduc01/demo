package com.example.demochauluc.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demochauluc.entities.auth.User;

public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByEmail(String email);
    
    public boolean existsByEmail(String email);
}
