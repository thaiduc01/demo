package com.example.demochauluc.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demochauluc.entities.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    public Optional<Role> findByRole(String role); 
    public boolean existsByRole(String role);
}
