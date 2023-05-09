package com.example.demochauluc.dtos;


import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.demochauluc.annotation.Unique;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuocGiaDto {

    private long id;
    
    @NotBlank(message = "Tên quốc gia không được phép trống")
    private String tenQuocGia;
    
    private long dienTich;
    
    private long soDan;
    
    @Length(max = 255, message = "Mô tả không được phép dài hơn 255 ký tự")
    private String mota;
    
    private long idChauLuc;
    
    @NotBlank(message = "Mã code không được phép trống")
    @Unique(message = "Mã code không được phép trùng lặp")
    private String code;
    
    
}
