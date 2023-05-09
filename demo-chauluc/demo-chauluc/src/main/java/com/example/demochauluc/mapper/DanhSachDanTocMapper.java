package com.example.demochauluc.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.example.demochauluc.dtos.DanhSachDanTocDto;
import com.example.demochauluc.entities.DanToc;
import com.example.demochauluc.entities.QuocGia;

@Mapper
public interface DanhSachDanTocMapper {

    @Mapping(source = "quocgia",target = "tenQuocGia",qualifiedByName = "dsQuocGia")
    @Mapping(source = "chauluc.tenChauLuc",target = "tenChauLuc")
    DanhSachDanTocDto toDto(DanToc dantoc);
    
    @Named(value = "dsQuocGia")
    static Set<String> dsQuocGia(Set<QuocGia> dsQuocGias){
        if(dsQuocGias == null) return Collections.emptySet();
        return dsQuocGias.stream().map(QuocGia::getTenQuocGia).collect(Collectors.toSet());
    }
    
    List<DanhSachDanTocDto> toDto(List<DanToc> entities);
    
    DanToc toEntity(DanhSachDanTocDto dto);
}

