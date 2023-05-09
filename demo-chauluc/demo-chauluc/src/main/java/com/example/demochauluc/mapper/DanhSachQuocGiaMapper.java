package com.example.demochauluc.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.entities.QuocGia;

@Mapper
public interface DanhSachQuocGiaMapper {
    
    @Mapping(source = "chauluc.tenChauLuc",target = "tenChauLuc")
    DanhSachQuocGiaDto toDto(QuocGia entity);
    
    List<DanhSachQuocGiaDto> toDto(List<QuocGia> entities);
    
    QuocGia toEntity(DanhSachQuocGiaDto dto);
}
