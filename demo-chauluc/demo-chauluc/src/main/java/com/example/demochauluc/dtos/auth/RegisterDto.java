package com.example.demochauluc.dtos.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Size(min = 3, max = 30)
    private String name;
    
    @Email(message = "Không đúng form email")
    private String email;
    
    @Pattern(regexp="[a-zA-Z0-9]{8,12}",message = "Mật khẩu ít nhất 8 ký tự và nhiều nhất 12 ký tự, phải bao gồm ít nhất 1 chữ hoa, 1 chữ thường và 1 số")
    private String password;
    
    @Size(min = 10,max = 10)
    private String mobileNo;
}
