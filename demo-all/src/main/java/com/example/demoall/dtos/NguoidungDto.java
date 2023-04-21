package com.example.demoall.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NguoidungDto {

    private Long id;
    
    @NotEmpty(message = "Name cannot be null")
    private String name;
    
    @Size(max= 255)
    @Email(message = "Email needs correct form a@gmail.com")
    @NotBlank(message = "email cannot be null")
    private String email;
    
    @Size(max = 60,message = "password cannot be more than 60 characters")
    @NotBlank(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$",message = "Password must to be at least 8 chars, 1 number, 1 upper case, 1 lower case letter, 1 special char, no spaces")
    private String password;
}
