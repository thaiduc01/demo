package com.example.demochauluc.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.entities.NgonNgu;
import com.example.demochauluc.entities.QuocGia;

@Mapper
public interface NgonNguMapper {
    
    @Mapping(source = "quocgia",target = "idQuocGia",qualifiedByName = "toQuocGiaId")
    NgonNguDto toDto(NgonNgu entity);
    
    
    List<NgonNguDto> toDto(List<NgonNgu> entities);
    
    @Named("toQuocGiaId")
    static Set<Long> toQuocGiaId(Set<QuocGia> dsQuocGia) {
        if (dsQuocGia == null) return Collections.emptySet();
        return dsQuocGia.stream().map(QuocGia::getId).collect(Collectors.toSet());
    }
    
    NgonNgu toEntity(NgonNguDto dto);
    
    NgonNgu updateNgonngu(NgonNguDto dto, @MappingTarget NgonNgu entity);
}
