package com.example.demochauluc.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import lombok.Data;

@Data
public class Paging<T> {

    private long totalPages;
    
    private long totalElements;
    
    private long pageSize;
    
    private long pageNumber;
    
    private List<T> contents;
    
    private Paging(List<T> dtos) {
        this.contents = dtos;
    }

    private Paging(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.contents = page.getContent();
    }
    
    private Paging(Page<?> entities, List<T> dtos) {
        this.totalPages = entities.getTotalPages();
        this.totalElements = entities.getTotalElements();
        this.pageSize = entities.getPageable().getPageSize();
        this.pageNumber = entities.getPageable().getPageNumber();
        this.contents = dtos;
    }
    
    public static <T> Paging<T> of(List<T> dtos) {
        return new Paging<>(dtos);
    }
    
    public static <T> Paging<T> of(Page<T> entities) {
        return new Paging<>(entities);
    }
    
    public static <T> Paging<T> of(Page<?> entities, List<T> dtos) {
        return new Paging<>(entities, dtos);
    }
}
