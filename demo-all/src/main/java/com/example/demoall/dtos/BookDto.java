package com.example.demoall.dtos;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;

import com.example.demoall.annotation.Author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long id;
 
    @NotNull(message =" book name cannot be null")
    @Author(message = "author list is api, c#,java, python,php")
    private String name;
 
    private int price;

    private String description;

    private int quantity;
    
    private Set<NguoidungDto> nguoiDung = new HashSet<>();
}
