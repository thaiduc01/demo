package com.example.demochauluc.Service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demochauluc.Service.ChauLucService;
import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.entities.ChauLuc;
import com.example.demochauluc.mapper.ChauLucMapper;
import com.example.demochauluc.repository.ChauLucRepository;
import com.example.demochauluc.utils.Paging;

@Service
public class ChauLucImpl implements ChauLucService{

    @Autowired
    private ChauLucRepository repository;
    
    @Autowired
    private ChauLucMapper mapper;
    
    @Override
    public List<ChauLucDto> getAllChauLuc(){
        List<ChauLuc> chauluc = repository.findAll();
        return mapper.toDto(chauluc);
    }
    
    @Override
    public Paging<ChauLucDto> getAllChauLucPaging(Pageable pageable){
        Page<ChauLucDto> result = repository.findAll(pageable).map(mapper::toDto);
        return Paging.of(result);
    }
    
    @Override
    public ChauLucDto getChauLucById(Long id) {
        ChauLuc chauluc = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(chauluc);
    }
    
    @Override
    public ChauLucDto createChauLuc(ChauLucDto dto) {
        ChauLuc newChauluc = mapper.toEntity(dto);
        repository.save(newChauluc);
        return mapper.toDto(newChauluc);
    }
    
    @Override
    public boolean updateChauLuc(ChauLucDto dto) {
        Long ChauLuc_id = dto.getId();
        ChauLuc existingChauLuc = repository.findById(ChauLuc_id).orElseThrow(EntityNotFoundException::new);
        existingChauLuc = mapper.updateChauLuc(dto, existingChauLuc);
        repository.save(existingChauLuc);
        return true;
    }
    @Override
    public boolean deleteChauLuc(Long id) {
        ChauLuc existingChauLuc = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(existingChauLuc);
        return true;
    }
}

