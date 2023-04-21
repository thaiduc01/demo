package com.example.demoall.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demoall.dtos.NguoidungDto;
import com.example.demoall.entity.NguoiDung;

public interface NguoiDungService {

    List<NguoidungDto> getAllNguoiDung();
    
    NguoidungDto getNguoiDungById(Long id);
    
    NguoidungDto createNewNguoidung(NguoidungDto dto);
    
    
    boolean updateNguoiDung(NguoidungDto dto);
    
    boolean deleteNguoiDung(Long id);
    
    Page<NguoiDung> getNguoiDungPagination(int pageNumber, int pageSize);
    
    List<NguoiDung> findNguoiDungByName(String name);
}
