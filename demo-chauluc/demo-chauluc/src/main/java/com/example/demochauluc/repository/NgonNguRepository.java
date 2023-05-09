package com.example.demochauluc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demochauluc.entities.NgonNgu;

public interface NgonNguRepository extends JpaRepository<NgonNgu,Long>,QuerydslPredicateExecutor<NgonNgu>{

}
