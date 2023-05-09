package com.example.demochauluc.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.entities.QuocGia;

@Mapper(componentModel = "spring",uses = {ChauLucMapper.class})
@Component
public interface QuocGiaMapper {

    @Mapping(source = "chauluc.id", target = "idChauLuc")
    QuocGiaDto toDto (QuocGia entity);
    
    List<QuocGiaDto> toDto(List<QuocGia> entities);
    
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "idChauLuc", target = "chauluc.id")
    QuocGia toEntity(QuocGiaDto dto);
    
    QuocGia updateQuocGia(QuocGiaDto dto,@MappingTarget QuocGia entity);

}
