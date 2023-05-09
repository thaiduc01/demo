package com.example.demochauluc.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.entities.NgonNgu;
import com.example.demochauluc.mapper.NgonNguMapper;

public class NgonNguMapperImpl implements NgonNguMapper{

    @Override
    public NgonNguDto toDto(NgonNgu entity) {
        if(entity == null) {
            return null;
        }
        
        NgonNguDto dto = new NgonNguDto();
        
        dto.setId(entity.getId());
        dto.setTenNgonNgu(entity.getTenNgonNgu());
        dto.setMota(entity.getMota());
        dto.setCode(entity.getCode());
        
        return dto;
    }
    
    @Override
    public List<NgonNguDto> toDto(List<NgonNgu> entities){
        List<NgonNguDto> dtos = new ArrayList<>();
        
        for(NgonNgu entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
    
    @Override
    public NgonNgu toEntity(NgonNguDto dto) {
        if(dto == null) {
            return null;
        }
        
        NgonNgu entity = new NgonNgu();
        
        entity.setId(dto.getId());
        entity.setTenNgonNgu(dto.getTenNgonNgu());
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
    
    @Override
    public NgonNgu updateNgonngu(NgonNguDto dto, NgonNgu entity) {
        if(dto == null || entity == null) {
            return null;
        }
        
        entity.setTenNgonNgu(dto.getTenNgonNgu());
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
}
