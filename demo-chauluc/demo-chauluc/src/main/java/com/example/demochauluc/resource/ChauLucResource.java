package com.example.demochauluc.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.ChauLucController;
import com.example.demochauluc.dtos.ChauLucDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class ChauLucResource extends RepresentationModel<ChauLucResource>{
    
    @Getter
    private final ChauLucDto dto;
    
    public ChauLucResource(ChauLucDto dto) {
        this.dto = dto;
        addLinks();
    }
    
    private void addLinks() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).getDSChauLucPaging(Pageable.ofSize(Integer.MAX_VALUE))).withRel("GetDSChauLucPaging"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).getChauLucById(dto.getId())).withRel("getbyid"));
    }
}
