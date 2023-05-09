package com.example.demochauluc.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.DanTocController;
import com.example.demochauluc.dtos.DanTocDto;

public class DanTocResource extends RepresentationModel<DanTocResource> {

    private final DanTocDto dto;
    
    public DanTocResource(DanTocDto dto) {
        this.dto = dto;
        
        addLinks();
    }
    
    private void addLinks() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).getDanTocById(dto.getId())).withSelfRel());
        //add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).updateDanToc(dto,dto.getId(), null)).withRel("update"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).deleteDanToc(dto.getId())).withRel("delete"));
    }

}
