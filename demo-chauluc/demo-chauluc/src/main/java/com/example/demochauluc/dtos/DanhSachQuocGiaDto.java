package com.example.demochauluc.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DanhSachQuocGiaDto {
    
    private String tenChauLuc;

    private String tenQuocGia;
    
    private long dienTich;
    
    private long soDan;
    
    private String mota;
    
    private String code;
    
}
