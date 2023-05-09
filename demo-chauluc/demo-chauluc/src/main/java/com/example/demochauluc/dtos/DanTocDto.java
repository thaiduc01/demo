package com.example.demochauluc.dtos;

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
@AllArgsConstructor
@NoArgsConstructor
public class DanTocDto {

    private long id;
    
    @NotBlank(message = "Tên dân tộc không được phép trống")
    private String tenDanToc;
    
    @Length(max = 255, message = "Mô tả không được phép dài hơn 255 ký tự")
    private String mota;
    
    @NotBlank(message = "Mã code không được phép trống")
    @Unique(message = "Mã code không được phép trùng lặp")
    private String code;
    
    private Set<Long> idQuocGias;
    
    private long idChauLuc;
}
