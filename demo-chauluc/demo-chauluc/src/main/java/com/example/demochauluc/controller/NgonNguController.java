package com.example.demochauluc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.resource.NgonNguResource;
import com.example.demochauluc.service.NgonNguService;
import com.example.demochauluc.utils.Paging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/ngonngu")
public class NgonNguController {

    @Autowired
    private NgonNguService service;
    
    @GetMapping
    @Operation(summary = "Lấy tất cả danh sách Ngôn Ngữ, link tới getAllPaging và getById ")
    public ResponseEntity<CollectionModel<NgonNguResource>> getAllNgonNgu(){
        List<NgonNguDto> dtos = service.getAllNgonNgu();
        List<NgonNguResource> resources = dtos.stream().map(NgonNguResource::new).collect(Collectors.toList());
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NgonNguController.class).getAllNgonNgu()).withSelfRel();
        CollectionModel<NgonNguResource> collectionModel = CollectionModel.of(resources, link);
        return ResponseEntity.ok(collectionModel);
        
    } 
    
    @GetMapping("/getAll")
    @Operation(summary = "Danh sách ngôn ngữ theo trang")
    public ResponseEntity<Paging<NgonNguDto>> getAllNgonNguPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllNgonNguPaging(pageable));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Danh sách ngôn ngữ theo id",parameters = {
            @Parameter(name = "id",description = "id ngôn ngữ cần lấy danh sách", required = false)
    })
    public ResponseEntity<NgonNguDto> getNgonNguById(@PathVariable(name = "id",required = true)long id){
        NgonNguDto dto = service.getNgonNguByID(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(dto);
        }
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Tạo mới ngôn ngữ")
    public ResponseEntity<NgonNguDto> createNgonNgu(@RequestBody @Valid NgonNguDto dto){
        return ResponseEntity.ok(service.createNgonNgu(dto));
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa ngôn ngữ",parameters = {
            @Parameter(name = "id", description = "id ngôn ngữ cần chỉnh sửa", required = false)
    })
    public ResponseEntity<Void> updateNgonNgu(@PathVariable(name = "id",required = true)long id,@RequestBody NgonNguDto dto){
        dto.setId(id);
        boolean success = service.updateNgonNgu(dto);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa ngôn ngữ theo id", parameters = {
            @Parameter(name = "id", description = "id ngôn ngữ cần xóa", required =  false)
    })
    public ResponseEntity<Void> deleteNgonNgu(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteNgonNgu(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("tim-kiem-theo-ten-quoc-gia")
    @Operation(summary = "Tìm kiếm ngôn ngữ theo tên quốc gia", parameters = {
            @Parameter(name = "tenQuocGia",description = "Tên quốc gia ngôn ngữ cần tìm",required = false)
    })
    public ResponseEntity<List<DanhSachNgonNguDto>> findNgonNguByNameQuocGia(@PathParam(value = "tenQuocGia")String tenQuocGia){
        return ResponseEntity.ok(service.findByNameQuocGia(tenQuocGia));
    }
}
