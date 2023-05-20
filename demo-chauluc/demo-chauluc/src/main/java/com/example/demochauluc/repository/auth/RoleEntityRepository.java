package com.example.demochauluc.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demochauluc.entities.auth.Role;

public interface RoleEntityRepository extends JpaRepository<Role, Long>{
    public Optional<Role> findRoleByRole(String role);
}

