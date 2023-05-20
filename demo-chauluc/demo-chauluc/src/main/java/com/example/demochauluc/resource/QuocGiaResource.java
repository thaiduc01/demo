package com.example.demochauluc.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.QuocGiaController;
import com.example.demochauluc.dtos.QuocGiaDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class QuocGiaResource extends RepresentationModel<QuocGiaResource>{

    @Getter
    private final QuocGiaDto dto;
    
    public QuocGiaResource(QuocGiaDto dto) {
        this.dto = dto;
        addLinks();
    }
    
    public void addLinks() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuocGiaController.class).getAllQuocGiaPaging(Pageable.ofSize(Integer.MAX_VALUE))).withRel("GetDSQuocGiaPaging"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuocGiaController.class).getQuocGiaById(dto.getId())).withRel("GetById"));
    }
}
