package com.example.demochauluc.Service;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.utils.Paging;

public interface QuocGiaService {

    Paging<QuocGiaDto> getAllQuocGiaPaging(Pageable pageable);
    
    QuocGiaDto getQuocGiaById(Long id);
    
    QuocGiaDto createQuocGia(QuocGiaDto dto);
    
    boolean updateQuocGia(QuocGiaDto dto);
    
    boolean deleteQuocGia(Long id);
    
    Paging<DanhSachQuocGiaDto> findQuocGiaByChauLuc(String tenChauLuc,Pageable pageable);
    
}
