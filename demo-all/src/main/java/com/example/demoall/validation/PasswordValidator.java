//package com.example.demoall.validation;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.example.demoall.exception.InvalidNguoiDungDataException;
//
//public class PasswordValidator {
//    
//    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,}$";
//    
//    private Pattern pattern;
//    
//    public PasswordValidator() {
//        pattern =Pattern.compile(PASSWORD_REGEX);
//    }
//    
//    public void ckeckPassword(String password) {
//        Matcher matcher = pattern.matcher(password);
//        if(!matcher.matches()) {
//            throw new InvalidNguoiDungDataException(String.format("Password must to be at least 8 chars, 1 number, 1 upper case," +
//                    " 1 lower case letter, 1 special char, no spaces"));
//        }
//    }
//}
//
