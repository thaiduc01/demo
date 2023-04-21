package com.example.demoall.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoall.dtos.BookDto;
import com.example.demoall.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;
    
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook(){
        return ResponseEntity.ok(service.getAllBook());
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id",required = true) Long id){
        BookDto bookdto = service.getBookById(id);
        if(bookdto == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<BookDto> createNewBook(@RequestBody @Valid BookDto dto){
        return ResponseEntity.ok(service.createNewBook(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable(name = "id",required = true) Long id, @RequestBody BookDto dto){
        dto.setId(id);
        boolean success = service.updateBook(dto);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id",required = true)Long id){
        boolean success = service.deleteBook(id);
        
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
}
