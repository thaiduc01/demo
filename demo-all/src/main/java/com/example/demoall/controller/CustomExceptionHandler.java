package com.example.demoall.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demoall.exception.ErrorDetails;
import com.example.demoall.exception.InvalidNguoiDungDataException;
import com.example.demoall.exception.InvalidNguoiDungIdentifierException;

@ControllerAdvice
public class CustomExceptionHandler {

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
    
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDetails> handlerNguoidung(MethodArgumentNotValidException ex){
//        BindingResult error = ex.getBindingResult();
//
//        String errorMessage = "Khong biet loi la gi.";
//        if (error.getFieldError() != null) {
//            errorMessage = error.getFieldError().getDefaultMessage();
//        }
//
//        ErrorDetails errResult = new ErrorDetails(errorMessage);
//
//        List<String> fieldErrorMessages = new ArrayList<>();
//        error.getFieldErrors().forEach(e -> {
//            fieldErrorMessages.add(e.getDefaultMessage());
//        });
//        
//        errResult.setErrorDetail(fieldErrorMessages);
//
//        //return ResponseEntity.badRequest().body(errResult);
//        
//        return ResponseEntity.ok(errResult);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        
        ObjectError firstGlobalError = bindingResult.getGlobalError();
        ErrorDetails errorResponse = new ErrorDetails(firstGlobalError == null ? "Bad request paramenter" : firstGlobalError.getDefaultMessage());
        
        List<ErrorDetails> childLevelErrorResponses = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->  new ErrorDetails(fieldError.getDefaultMessage(), fieldError.getField()))
                .collect(Collectors.toList());
        errorResponse.setChildLevel(childLevelErrorResponses);
        
        // bad request status
        return ResponseEntity.badRequest().body(errorResponse);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
