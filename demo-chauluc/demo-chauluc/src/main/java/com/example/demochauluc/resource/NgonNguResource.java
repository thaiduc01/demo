package com.example.demochauluc.resource;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.NgonNguController;
import com.example.demochauluc.dtos.NgonNguDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class NgonNguResource extends RepresentationModel<NgonNguResource> {

    @Getter
    private final NgonNguDto dto;
    
    public NgonNguResource(NgonNguDto dto) {
        this.dto = dto;
        addLink();
    }
    
    public void addLink() {
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NgonNguController.class).getAllNgonNguPaging(Pageable.ofSize(Integer.MAX_VALUE))).withRel("GetDSNgonNguPaging"));
        add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NgonNguController.class).getNgonNguById(dto.getId())).withRel("getNgonNguById"));
    }
}
