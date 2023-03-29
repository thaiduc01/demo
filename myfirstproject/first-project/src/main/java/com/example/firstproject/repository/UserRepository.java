package com.example.firstproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.firstproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query(value="SELECT * FROM user", nativeQuery = true)
    List<UserEntity> getAllUser();
    
  //cu phap like %:keyword% co nghia la giong bat cu chu nao trong do thi se search ra ket qua
    @Query("select u from UserEntity u where u.name like %:keyword% or u.email like  %:keyword%")
    List<UserEntity> findUserByKeyword(@Param(value = "keyword") String keyword);

}
