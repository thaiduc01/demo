package com.example.demoall.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demoall.dtos.NguoidungDto;
import com.example.demoall.entity.NguoiDung;
import com.example.demoall.exception.InvalidNguoiDungDataException;
import com.example.demoall.exception.InvalidNguoiDungIdentifierException;
import com.example.demoall.mapper.NguoidungMapper;
import com.example.demoall.entity.QNguoiDung;
import com.example.demoall.repository.NguoiDungRepository;
import com.querydsl.jpa.impl.JPAQuery;


@Service
public class NguoiDungServiceImpl implements NguoiDungService{

    @Autowired
    private NguoidungMapper mapper;
    
    @Autowired
    private NguoiDungRepository repository;
    
    @PersistenceContext
    private EntityManager em;
    
//    private EmailValidator emailValidator;
//    
//    private PasswordValidator passwordValidator;
//    
    public NguoiDungServiceImpl() {
 //       emailValidator =  new EmailValidator();
//        passwordValidator = new PasswordValidator();
    }
//    
    @Override
    public List<NguoidungDto> getAllNguoiDung(){
        List<NguoiDung> nguoidung = repository.findAll();
        return mapper.toDto(nguoidung);
    }
    
    
    @Override
    public NguoidungDto getNguoiDungById(Long id) {
        if(id == null) {
            throw new InvalidNguoiDungIdentifierException("id cannot be null");
        }
        NguoiDung nguoiDung = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(nguoiDung);
        
    }
    
    @Override
    public NguoidungDto createNewNguoidung(NguoidungDto dto) {
        if(dto == null) {
            throw new InvalidNguoiDungDataException("Nguoi Dung account data cannot be  null");
        }
//        passwordValidator.ckeckPassword(dto.getPassword());
//        emailValidator.ckeckMail(dto.getEmail());
        
        NguoiDung newNguoiDung = mapper.toEntity(dto);
        repository.save(newNguoiDung);
        return mapper.toDto(newNguoiDung);
    }
    
    
    @Override
    public boolean updateNguoiDung(NguoidungDto dto) {
        Long NguoiDung_id = dto.getId();
        
        if(NguoiDung_id == null) {
            throw new InvalidNguoiDungIdentifierException("Id cannot be null");
        }
        NguoiDung existingNguoiDung = repository.findById(NguoiDung_id).orElseThrow(EntityNotFoundException::new);
        existingNguoiDung = mapper.updateEntity(dto, existingNguoiDung);
        repository.save(existingNguoiDung);
        return true;
    }
    
    @Override
    public boolean deleteNguoiDung(Long id) {
        NguoiDung existingNguoiDung = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(existingNguoiDung);
        return true;
    }
    
    @Override
    public Page<NguoiDung> getNguoiDungPagination(int pageNumber, int pageSize){
        Pageable pageable = null;
        pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable);
    }
    
    @Override
    public List<NguoiDung> findNguoiDungByName(String name){
        JPAQuery<NguoiDung> query = new JPAQuery<>(em);
        QNguoiDung nguoidung = QNguoiDung.nguoiDung;
        return query.from(nguoidung).where(nguoidung.name.eq(name)).fetch();
    }
}
