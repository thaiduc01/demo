package com.example.demoall.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demoall.exception.ErrorDetails;
import com.example.demoall.exception.InvalidNguoiDungDataException;
import com.example.demoall.exception.InvalidNguoiDungIdentifierException;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler({InvalidNguoiDungDataException.class,InvalidNguoiDungIdentifierException.class})
    public ResponseEntity<ErrorDetails> handleAsBadRequest(RuntimeException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
//    
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDetails> handlerNguoidung(MethodArgumentNotValidException ex){
//        BindingResult error = ex.getBindingResult();
//        ErrorDetails errResult = Optional.ofNullable(error.getFieldError())
//                .map(FieldError::getDefaultMessage)
//                .map(ErrorDetails::new)
//                .orElse(new ErrorDetails("Khong biet loi la gi"));
//        error.getFieldErrors().forEach(e -> {
//            System.out.println(e.getDefaultMessage());
//        });
//            return ResponseEntity.ok(errResult);
//
//    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handlerNguoidung(MethodArgumentNotValidException ex){
        BindingResult error = ex.getBindingResult();

        String errorMessage = "Khong biet loi la gi.";
        if (error.getFieldError() != null) {
            errorMessage = error.getFieldError().getDefaultMessage();
        }

        ErrorDetails errResult = new ErrorDetails(errorMessage);

        List<String> fieldErrorMessages = new ArrayList<>();
        error.getFieldErrors().forEach(e -> {
            fieldErrorMessages.add(e.getDefaultMessage());
        });
        
        errResult.setErrorDetail(fieldErrorMessages);

        //return ResponseEntity.badRequest().body(errResult);
        
        return ResponseEntity.ok(errResult);
    }
}
