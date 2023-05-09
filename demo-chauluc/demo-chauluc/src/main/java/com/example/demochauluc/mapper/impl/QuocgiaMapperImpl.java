package com.example.demochauluc.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.entities.QuocGia;
import com.example.demochauluc.mapper.QuocGiaMapper;

public class QuocgiaMapperImpl implements QuocGiaMapper{
   
    @Override
    public QuocGiaDto toDto(QuocGia entity) {
        if(entity == null) {
            return null;
        }
        
        QuocGiaDto dto = new QuocGiaDto();
        
        dto.setId(entity.getId());
        dto.setTenQuocGia(entity.getTenQuocGia());
        dto.setDienTich(entity.getDienTich());
        dto.setSoDan(entity.getSoDan());
        dto.setMota(entity.getMota());
        dto.setIdChauLuc(entity.getChauluc().getId());    
        return dto;
    }
    
    @Override
    public List<QuocGiaDto> toDto(List<QuocGia> entities){
        List<QuocGiaDto> dtos = new ArrayList<>();
        for(QuocGia entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
    
    
    @Override
    public QuocGia toEntity(QuocGiaDto dto) {
        if(dto == null) {
            return null;
        }
        QuocGia entity = new QuocGia();
        
        entity.setTenQuocGia(dto.getTenQuocGia());
        entity.setDienTich(dto.getDienTich());
        entity.setSoDan(dto.getSoDan());
        entity.setMota(dto.getMota());
        
        
        return entity;
    }
    
    
    @Override
    public QuocGia updateQuocGia(QuocGiaDto dto, QuocGia entity) {
        if(dto == null || entity == null) {
            return null;
        }
        
        entity.setTenQuocGia(dto.getTenQuocGia());
        entity.setDienTich(dto.getDienTich());
        entity.setSoDan(dto.getSoDan());
        entity.setMota(dto.getMota());
        
        return entity;
    }
}
