package com.example.demochauluc.dtos;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DanhSachDanTocDto {
    
    private String tenChauLuc;
    
    private Set<String> tenQuocGia;
    
    private String tenDanToc;
    
    private String mota;
    
    private String code;
}
