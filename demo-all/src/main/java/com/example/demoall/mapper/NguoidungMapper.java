package com.example.demoall.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.demoall.dtos.NguoidungDto;
import com.example.demoall.entity.NguoiDung;

@Mapper(uses = {BookMapper.class})
public interface NguoidungMapper {

    NguoidungDto toDto(NguoiDung nguoidung);
    
    List<NguoidungDto> toDto (List<NguoiDung> entities);
    
    NguoiDung toEntity(NguoidungDto dto);
    
    NguoiDung updateEntity(NguoidungDto dto,@MappingTarget NguoiDung entity);
    
}
