package com.example.demochauluc.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.ChauLucController;
import com.example.demochauluc.dtos.ChauLucDto;

public class ChauLucResource extends RepresentationModel<ChauLucResource>{

    private final ChauLucDto dto;
    
    public ChauLucResource(ChauLucDto dto) {
        this.dto = dto;
        addLinks();
    }
    
    private void addLinks() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).getChauLucById(dto.getId())).withRel("getbyid"));
       // add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).updateChauLuc(dto.getId(),dto,null)).withRel("updatebyid"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).deleteChauLuc(dto.getId())).withRel("removebyid"));
    }
}
