package com.example.demochauluc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.dtos.DanhSachDanTocDto;
import com.example.demochauluc.utils.Paging;
public interface DanTocService {

    //Paging<DanTocDto> getAllDanTocPaging(int pageNumber, int pageSize);
    List<DanTocDto> getAllDanToc();
    
    Paging<DanTocDto> getAllDanTocPaging(Pageable pageable);
    
    DanTocDto getDanTocByID(Long id);
    
    DanTocDto createDanToc(DanTocDto dto);
    
    boolean updateDanToc(DanTocDto dto);
    
    boolean deleteDanToc(Long id);
    
    Paging<DanhSachDanTocDto> findByNameQuocGia(String tenQuocGia,Pageable pageable);
    
    Paging<DanhSachDanTocDto> findByNameChauLuc(String tenChauLuc,Pageable pageable);
}
