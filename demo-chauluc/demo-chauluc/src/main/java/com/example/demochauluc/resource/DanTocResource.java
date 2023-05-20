package com.example.demochauluc.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.DanTocController;
import com.example.demochauluc.dtos.DanTocDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class DanTocResource extends RepresentationModel<DanTocResource> {

    @Getter
    private final DanTocDto dto;
    
    public DanTocResource(DanTocDto dto) {
        this.dto = dto;
        
        addLinks();
    }
    
    private void addLinks() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).getAllDanTocPaging(Pageable.ofSize(Integer.MAX_VALUE))).withRel("GetDSDanTocPaging"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).getDanTocById(dto.getId())).withSelfRel());
    }

}
