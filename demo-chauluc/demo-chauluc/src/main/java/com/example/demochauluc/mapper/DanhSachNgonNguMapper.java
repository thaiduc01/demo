package com.example.demochauluc.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.entities.NgonNgu;
import com.example.demochauluc.entities.QuocGia;

@Mapper
public interface DanhSachNgonNguMapper {
    
    @Mapping(source = "quocgia",target = "tenQuocGia",qualifiedByName = "dsQuocGia")
    DanhSachNgonNguDto toDto(NgonNgu entity);
    
    @Named("dsQuocGia")
    static Set<String> dsQuocGia(Set<QuocGia> dsQuocGias){
        if(dsQuocGias == null) return Collections.emptySet();
        return dsQuocGias.stream().map(QuocGia::getTenQuocGia).collect(Collectors.toSet());
    }
    
    List<DanhSachNgonNguDto> toDto(List<NgonNgu> entities);
    
    NgonNgu toEntity(DanhSachNgonNguDto dto);
}
