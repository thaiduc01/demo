package com.example.demochauluc.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.utils.Paging;

public interface NgonNguService {
    
    Paging<NgonNguDto> getAllNgonNguPaging(Pageable pageable);
    
    NgonNguDto getNgonNguByID(Long id);
    
    NgonNguDto createNgonNgu(NgonNguDto dto);
    
    boolean updateNgonNgu(NgonNguDto dto);
    
    boolean deleteNgonNgu(Long id);
    
    List<DanhSachNgonNguDto> findByNameQuocGia(String tenQuocGia);
}
