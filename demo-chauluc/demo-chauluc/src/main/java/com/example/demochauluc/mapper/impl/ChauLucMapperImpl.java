package com.example.demochauluc.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.entities.ChauLuc;
import com.example.demochauluc.mapper.ChauLucMapper;

public class ChauLucMapperImpl implements ChauLucMapper{

    @Override
    public ChauLucDto toDto(ChauLuc entity) {
        if(entity == null) {
            return null;
        }
        ChauLucDto dto = new ChauLucDto();
        
        dto.setId(entity.getId());
        dto.setTenChauLuc(entity.getTenChauLuc());
        dto.setDienTich(entity.getDienTich());
        dto.setSoQuocGia(entity.getSoQuocGia());
        dto.setMota(entity.getMota());
        dto.setCode(entity.getCode());
        return dto;
    }
    
    @Override
    public List<ChauLucDto> toDto(List<ChauLuc> entities){
        List<ChauLucDto> dtos = new ArrayList<>();
        for(ChauLuc entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
    
    @Override
    public ChauLuc toEntity(ChauLucDto dto) {
        if(dto == null) {
            return null;
        }
        ChauLuc entity = new ChauLuc();
        
        entity.setId(dto.getId());
        entity.setTenChauLuc(dto.getTenChauLuc());
        entity.setDienTich(dto.getDienTich());
        entity.setSoQuocGia(dto.getSoQuocGia());
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
    
    @Override
    public ChauLuc updateChauLuc(ChauLucDto dto,ChauLuc entity) {
        if(dto == null || entity == null) {
            return null;
        }
        
        entity.setTenChauLuc(dto.getTenChauLuc());
        entity.setDienTich(dto.getDienTich());
        entity.setSoQuocGia(dto.getSoQuocGia());
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
}
