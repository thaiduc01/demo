package com.example.demochauluc.handler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demochauluc.exception.EntityNotFoundException;
import com.example.demochauluc.exception.ErrorDetails;
import com.example.demochauluc.exception.InvalidInputException;
import com.example.demochauluc.exception.ResponseObject;


@ControllerAdvice
public class ValidationHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        BindingResult bindingResult = ex.getBindingResult();
//        
//        ObjectError firstGlobalError = bindingResult.getGlobalError();
//        ErrorDetails errorResponse = new ErrorDetails(firstGlobalError == null ? "Bad request paramenter" : firstGlobalError.getDefaultMessage());
//        
//        List<ErrorDetails> childLevelErrorResponses = bindingResult.getFieldErrors()
//                .stream()
//                .map(fieldError ->  new ErrorDetails(fieldError.getDefaultMessage(), fieldError.getField()))
//                .collect(Collectors.toList());
//        errorResponse.setChildLevel(childLevelErrorResponses);
//        
//        // bad request status
//        return ResponseEntity.badRequest().body(errorResponse);
//    }
//    
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException ex) {
//        return ResponseEntity.notFound().build();
//    }
    
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ResponseObject> handleInvalidInputException(InvalidInputException ex){
        Map<String, String> error = ex.getResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
        ResponseObject ro = new ResponseObject(error,"fail");
        return ResponseEntity.badRequest().body(ro);
    }
    
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleAsBadRequest(RuntimeException ex){
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
}
