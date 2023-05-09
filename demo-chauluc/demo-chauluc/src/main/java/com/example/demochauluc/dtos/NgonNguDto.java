package com.example.demochauluc.dtos;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.demochauluc.annotation.Unique;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NgonNguDto {

    private long id;
    
    @NotBlank(message = "tên ngôn ngữ không được phép trống")
    private String tenNgonNgu;
    
    @Length(max = 255, message = "Mô tả không được phép dài hơn 255 ký tự")
    private String mota;
    
    @NotBlank(message = "Mã code không được phép trống")
    @Unique(message = "Mã code không được phép trùng lặp")
    private String code;
    
    private Set<Long> idQuocGia = new HashSet<>();
}
