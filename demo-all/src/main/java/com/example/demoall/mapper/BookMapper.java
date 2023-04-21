package com.example.demoall.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.demoall.dtos.BookDto;
import com.example.demoall.entity.Book;

@Mapper
public interface BookMapper {

    BookDto toDto(Book permissions);
    
    List<BookDto> toDto (List<Book> toEntity);
    
    Book toEntity (BookDto dto);
    
    @Mapping(target = "id",ignore = true)
    public void updateEntity(BookDto dto, @MappingTarget Book book);
}
