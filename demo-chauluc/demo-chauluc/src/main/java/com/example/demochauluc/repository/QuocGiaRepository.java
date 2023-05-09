package com.example.demochauluc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.demochauluc.entities.QuocGia;


public interface QuocGiaRepository extends JpaRepository<QuocGia, Long>,QuerydslPredicateExecutor<QuocGia>{
}
