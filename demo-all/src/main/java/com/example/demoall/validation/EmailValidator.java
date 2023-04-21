//package com.example.demoall.validation;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.Part;
//
//import org.apache.logging.log4j.util.Strings;
//
//import com.example.demoall.exception.InvalidNguoiDungDataException;
//
//
//public class EmailValidator {
//
//    private static final int MAX_EMAIL_lENGTH = 255;
//    
//    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//    
//    private Pattern pattern;
//    
//    public EmailValidator() {
//        pattern = Pattern.compile(EMAIL_REGEX);
//    }
//    
//    public void ckeckMail(String email) {
//        if(Strings.isNullOrEmpty(email)) {
//           throw new InvalidNguoiDungDataException("The Email cannot be null or empty");
//        }
//        
//        if(email.length()>MAX_EMAIL_lENGTH) {
//            throw new InvalidNguoiDungDataException(String.format("The Email is too long: max number of chars is %s", MAX_EMAIL_lENGTH));        
//            }
//        
//        Matcher matcher = pattern.matcher(email);
//        if(!matcher.matches()) {
//            throw new InvalidNguoiDungDataException(String.format("The Email provided %s is not format valid", email));
//        }
//    }
//}
