package com.example.demoall.annotation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorValidator implements ConstraintValidator<Author, String> {
    
    List<String> authors = Arrays.asList("api","c#","java","python","php");
    
    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {
        return authors.contains(value);
    }

}
