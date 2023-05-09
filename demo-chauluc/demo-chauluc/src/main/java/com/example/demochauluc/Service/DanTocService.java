package com.example.demochauluc.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.dtos.DanhSachDanTocDto;
import com.example.demochauluc.utils.Paging;
public interface DanTocService {

    //Paging<DanTocDto> getAllDanTocPaging(int pageNumber, int pageSize);
    
    Paging<DanTocDto> getAllDanTocPaging(Pageable pageable);
    
    DanTocDto getDanTocByID(Long id);
    
    DanTocDto createDanToc(DanTocDto dto);
    
    boolean updateDanToc(DanTocDto dto);
    
    boolean deleteDanToc(Long id);
    
    List<DanhSachDanTocDto> findByNameQuocGia(String tenQuocGia);
    
    List<DanhSachDanTocDto> findByNameChauLuc(String tenChauLuc);
}
