package com.example.demochauluc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.utils.Paging;

public interface NgonNguService {
    
    List<NgonNguDto> getAllNgonNgu();
    
    Paging<NgonNguDto> getAllNgonNguPaging(Pageable pageable);
    
    NgonNguDto getNgonNguByID(Long id);
    
    NgonNguDto createNgonNgu(NgonNguDto dto);
    
    boolean updateNgonNgu(NgonNguDto dto);
    
    boolean deleteNgonNgu(Long id);
    
    Paging<DanhSachNgonNguDto> findByNameQuocGia(String tenQuocGia,Pageable pageable);
}
