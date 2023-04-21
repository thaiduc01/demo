package com.example.demoall.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoall.entity.NguoiDung;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long>{

    //public List<NguoiDung> findNguoiDungByNameQueryDLS(String name);
}
