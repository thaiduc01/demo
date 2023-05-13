package com.example.demochauluc.resource;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demochauluc.controller.ChauLucController;
import com.example.demochauluc.dtos.ChauLucDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
public class ChauLucResource extends RepresentationModel<ChauLucResource>{

    /*
     * dto (private access) cần được cung cấp để môi trường bên ngoài có thể lấy được dữ liệu
     * - Thêm method getter trả về trường dữ liệu dto này
     * - Hoặc sử dụng lombok annotation (@Getter , @Data, @Value, ..)
     */
    @Getter
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
