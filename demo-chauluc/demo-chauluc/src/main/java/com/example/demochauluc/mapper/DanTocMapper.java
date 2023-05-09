package com.example.demochauluc.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.entities.DanToc;
import com.example.demochauluc.entities.QuocGia;

@Mapper
public interface DanTocMapper {

    @Mapping(source = "quocgia",target = "idQuocGias",qualifiedByName = "toQuocGiaId")
    @Mapping(source = "chauluc.id",target = "idChauLuc")
    DanTocDto toDto(DanToc entity);
    
    @Named("toQuocGiaId")
    static Set<Long> toQuocGiaId(Set<QuocGia> dsQuocGia){
        if(dsQuocGia == null) return Collections.emptySet();
        return dsQuocGia.stream().map(QuocGia::getId).collect(Collectors.toSet());
    }
    
    List<DanTocDto> toDto(List<DanToc> entities);
    
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "idChauLuc",target = "chauluc.id")
    DanToc toEntity(DanTocDto dto);
    
    DanToc updateDanToc(DanTocDto dto,@MappingTarget DanToc entity);
}
