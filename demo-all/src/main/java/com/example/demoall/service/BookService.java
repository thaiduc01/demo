package com.example.demoall.service;

import java.util.List;

import com.example.demoall.dtos.BookDto;
import com.example.demoall.entity.NguoiDung;

public interface BookService {

    List<BookDto> getAllBook();
    
    BookDto getBookById(Long id);
    
    BookDto createNewBook(BookDto dto);
    
    boolean updateBook(BookDto dto);
    
    boolean deleteBook(Long id);
    
}
