package com.example.demochauluc.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.example.demochauluc.annotation.ListTenChauLuc;
import com.example.demochauluc.annotation.Unique;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChauLucDto {

    private Long id;
    @NotNull(message = "Tên Châu Lục không được trống")
    @ListTenChauLuc(message = "Tên Châu Lục không đúng")
    private String tenChauLuc;
    private long dienTich;
    private long soQuocGia;
    
    @Length(max = 255, message = "Mô tả không được phép dài hơn 255 ký tự")
    private String mota;
    
    @NotBlank(message = "Mã code không được trống")
    @Unique(message = "Mã code không được phép trùng lặp")
    private String code;
}
