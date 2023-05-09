package com.example.demochauluc.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.entities.DanToc;
import com.example.demochauluc.mapper.DanTocMapper;

public class DanTocMapperImpl implements DanTocMapper{

    @Override
    public DanTocDto toDto(DanToc entity) {
        if(entity == null) {
            return null;
        }
        DanTocDto dto = new DanTocDto();
        
        dto.setId(entity.getId());
        dto.setTenDanToc(entity.getTenDanToc());
        dto.setMota(entity.getMota());
        dto.setCode(entity.getCode());
        dto.setIdChauLuc(entity.getChauluc().getId());
        return dto;
    }
    
    @Override
    public List<DanTocDto> toDto(List<DanToc> entities){
        List<DanTocDto> dtos = new ArrayList<>();
        for(DanToc entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
    
    @Override
    public DanToc toEntity(DanTocDto dto) {
        if(dto == null) {
            return null;
        }
        DanToc entity = new DanToc();
        
        entity.setId(dto.getId());
        entity.setTenDanToc(dto.getTenDanToc());
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
    
    @Override
    public DanToc updateDanToc(DanTocDto dto,DanToc entity) {
        if(dto == null || entity == null) {
            return null;
        }
        entity.setTenDanToc(dto.getTenDanToc());;
        entity.setMota(dto.getMota());
        entity.setCode(dto.getCode());
        
        return entity;
    }
}
