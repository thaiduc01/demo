package com.example.demochauluc.annotation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TenChauLucValidator implements ConstraintValidator<ListTenChauLuc,String>{
    
    List<String> tenchauluc = Arrays.asList("Châu á","Châu phi","Bắc mỹ","Nam mỹ","Châu âu","Châu úc","Châu nam cực");
    
    public boolean isValid(String value,ConstraintValidatorContext context) {
        return tenchauluc.contains(value);
    }

}
