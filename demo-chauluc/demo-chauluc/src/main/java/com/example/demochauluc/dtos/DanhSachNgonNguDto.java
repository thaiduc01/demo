package com.example.demochauluc.dtos;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DanhSachNgonNguDto {

   private Set<String> tenQuocGia;
    
    private String tenNgonNgu;
    
    private String mota;
    
    private String code;
}
