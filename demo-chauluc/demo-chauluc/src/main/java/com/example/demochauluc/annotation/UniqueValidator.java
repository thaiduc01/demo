package com.example.demochauluc.annotation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final List<String> values = new ArrayList<>();

    @Override
    public void initialize(Unique constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (values.contains(value)) {
            return false;
        } else {
            values.add(value);
            return true;
        }
    }
}
