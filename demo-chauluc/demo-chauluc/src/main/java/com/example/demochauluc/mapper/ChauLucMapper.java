package com.example.demochauluc.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.entities.ChauLuc;

@Mapper
public interface ChauLucMapper {

    ChauLucDto toDto(ChauLuc entity);
    
    List<ChauLucDto> toDto(List<ChauLuc> entities);
    
    ChauLuc toEntity(ChauLucDto dto);
    
    ChauLuc updateChauLuc(ChauLucDto dto,@MappingTarget ChauLuc entity);
}
