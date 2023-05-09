package com.example.demochauluc.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.utils.Paging;

public interface ChauLucService {
    
    List<ChauLucDto> getAllChauLuc();

    Paging<ChauLucDto> getAllChauLucPaging(Pageable pageable);
    
    ChauLucDto getChauLucById(Long id);
    
    ChauLucDto createChauLuc(ChauLucDto dto);
    
    boolean updateChauLuc(ChauLucDto dto);
    
    boolean deleteChauLuc(Long id);
}
