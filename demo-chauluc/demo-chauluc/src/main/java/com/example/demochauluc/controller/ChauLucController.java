package com.example.demochauluc.controller;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.Exception.InvalidInputException;
import com.example.demochauluc.Exception.ResponseObject;
import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.resource.ChauLucResource;
import com.example.demochauluc.service.ChauLucService;
import com.example.demochauluc.utils.Paging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/chauluc")
public class ChauLucController {

    @Autowired
    private ChauLucService service;
    
    @GetMapping
    @Operation(summary = "Lấy tất cả danh sách Châu Lục, link tới getAllPaging và getById ")
    public ResponseEntity<CollectionModel<ChauLucResource>> getAllChauLuc(){
        List<ChauLucDto> dtos = service.getAllChauLuc();
        List<ChauLucResource> resource = dtos.stream().map(ChauLucResource::new).collect(Collectors.toList());
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).getAllChauLuc()).withSelfRel();
        CollectionModel<ChauLucResource> collectionModel = CollectionModel.of(resource,link);
        return ResponseEntity.ok().body(collectionModel); 
    }
    
    @Operation(summary = "Danh sách châu lục theo trang",parameters = {
            @Parameter(name = "page",description = "số thứ tự trang",required = false),
            @Parameter(name = "size",description = "số bản ghi trả về cho mỗi trang",required = false),
            @Parameter(name = "sort",description = "Sắp xếp", required = false)
    })
    @GetMapping("/getAll")
    public ResponseEntity<Paging<ChauLucDto>> getDSChauLucPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllChauLucPaging(pageable));
    }
    
    @GetMapping("/getById/{id}")
    @Operation(summary = "Danh sách châu lục theo id",parameters = {
            @Parameter(name = "id",description = "id của châu lục cần lấy danh sách",required = false)
    })
    public ResponseEntity<ChauLucDto> getChauLucById(@PathVariable(name = "id",required = true)long id){
        ChauLucDto chauluc = service.getChauLucById(id);
        if(chauluc == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(chauluc);
        }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Tạo mới châu lục")
    public ResponseEntity<ResponseObject> createNewChauLuc(@RequestBody @Valid ChauLucDto dto,BindingResult result,HttpServletRequest request){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        ChauLucDto cl = service.createChauLuc(dto);
        return ResponseEntity.ok(new ResponseObject(cl,"success"));
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa châu lục",parameters = {
            @Parameter(name = "id",description = " id châu lục cần chỉnh sửa",required = false)
    })
    public ResponseEntity<ResponseObject> updateChauLuc(@PathVariable(name = "id",required = true)long id,@RequestBody @Valid ChauLucDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        dto.setId(id);
        boolean success = service.updateChauLuc(dto);
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa châu lục",parameters = {
            @Parameter(name = "id",description = " id châu lục cần xóa",required = false)
    })
    public ResponseEntity<Void> deleteChauLuc(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteChauLuc(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
            }
    }
    
}
