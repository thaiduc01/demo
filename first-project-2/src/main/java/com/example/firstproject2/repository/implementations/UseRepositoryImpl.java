package com.example.firstproject2.repository.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.firstproject2.entities.UserEntity;
import com.example.firstproject2.repository.UserRepository;

@Repository
public class UseRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public List<UserEntity> findAll(){
        String query = "select * from user ";
        List<UserEntity> result = entityManager.createNativeQuery(query, UserEntity.class).getResultList();
        return result;
        
    }
    
    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }
    
    @Override
    @Transactional
    public void save(UserEntity userEntity) {
         entityManager.persist(userEntity);
    }
    
    @Override
    @Transactional
    public void delete(UserEntity userEntity) {
        entityManager.remove(userEntity);
    }
}
