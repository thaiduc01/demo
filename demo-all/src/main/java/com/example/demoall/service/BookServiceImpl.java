package com.example.demoall.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoall.dtos.BookDto;
import com.example.demoall.entity.Book;
import com.example.demoall.mapper.BookMapper;
import com.example.demoall.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper mapper;
    
    @Autowired
    private BookRepository repository;
    @Override
    public List<BookDto> getAllBook(){
        List<Book> book = repository.findAll();
        return mapper.toDto(book);
    }
    @Override
    public BookDto getBookById(Long id) {
        Book book = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(book);
    }
    @Override
    public BookDto createNewBook(BookDto dto) {
        Book newBook = mapper.toEntity(dto);
        repository.save(newBook);
        return mapper.toDto(newBook);
    }
    @Override
    public boolean updateBook(BookDto dto) {
       Long book_id = dto.getId();
       
       Book existingBook = repository.findById(book_id).orElseThrow(EntityNotFoundException::new);
       mapper.updateEntity(dto, existingBook);
       repository.save(existingBook);
       return true;
    }
    @Override
    public boolean deleteBook(Long id) {
        Book existingBook = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(existingBook);
        return true;
    }
    
}
