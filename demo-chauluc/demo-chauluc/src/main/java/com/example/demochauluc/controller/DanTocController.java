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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.Exception.InvalidInputException;
import com.example.demochauluc.Exception.ResponseObject;
import com.example.demochauluc.Service.DanTocService;
import com.example.demochauluc.dtos.DanTocDto;
import com.example.demochauluc.dtos.DanhSachDanTocDto;
import com.example.demochauluc.resource.ChauLucResource;
import com.example.demochauluc.resource.DanTocResource;
import com.example.demochauluc.utils.Paging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/dantoc")
public class DanTocController {

    @Autowired
    private DanTocService service;
    

    @GetMapping
    @Operation(summary = "Lấy tất cả danh sách Dân Tộc, link tới getAllPaging và getById")
    public ResponseEntity<CollectionModel<DanTocResource>> getAllDanToc(){
        List<DanTocDto> dto = service.getAllDanToc();
        List<DanTocResource> recource = dto.stream().map(DanTocResource::new).collect(Collectors.toList());
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DanTocController.class).getAllDanToc()).withSelfRel();
        CollectionModel<DanTocResource> collectionModel = CollectionModel.of(recource, link);
        return ResponseEntity.ok(collectionModel);
        
    }
    
    
    @Operation(summary = "Danh sách dân tộc theo trang",parameters = {
            @Parameter(name = "page",description = "Số thứ tự trang",required = false),
            @Parameter(name = "size",description = "Số bản ghi trả về cho mỗi trang", required = false),
            @Parameter(name = "sort",description = "sắp xếp", required =  false)
    })
    @GetMapping("/getAll")
    public ResponseEntity<Paging<DanTocDto>> getAllDanTocPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllDanTocPaging(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Danh sách dân tộc theo id",parameters = {
            @Parameter(name = "id",description = "id dân tộc cần lấy danh sách", required =  false)
    })
    public ResponseEntity<DanTocDto> getDanTocById(@PathVariable(name = "id",required = true)long id){
        DanTocDto dantoc = service.getDanTocByID(id);
        if(dantoc == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(dantoc);
        }
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Tạo mới dân tộc")
    public ResponseEntity<ResponseObject> createDanToc(@RequestBody @Valid DanTocDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        DanTocDto dantocs = service.createDanToc(dto);
        return ResponseEntity.ok(new ResponseObject(dantocs,"success"));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa dân tộc", parameters = {
            @Parameter(name = "id",description = "id dân tộc cần chỉnh sửa", required = false)
    })
    public ResponseEntity<ResponseObject> updateDanToc(@RequestBody @Valid DanTocDto dto,@PathVariable(name = "id",required = true)long id,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        dto.setId(id);
        boolean success = service.updateDanToc(dto);
        
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa dân tộc theo id", parameters = {
            @Parameter(name = "id",description = "id dân tộc cần xóa", required = false)
    })
    public ResponseEntity<Void> deleteDanToc(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteDanToc(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    

    @GetMapping("/tim-kiem-theo-ten-quoc-gia")
    @Operation(summary = "Tìm kiếm dân tộc theo tên quốc gia", parameters = {
            @Parameter(name = "tenQuocGia",description = "tên quốc gia muốn tìm", required =  false)
    })
    public ResponseEntity<List<DanhSachDanTocDto>> findDanTocByNameQuocGia(@PathParam(value = "tenQuocGia")String tenQuocGia){
        return ResponseEntity.ok(service.findByNameQuocGia(tenQuocGia));
    }
    
    @GetMapping("/tim-kiem-theo-ten-chau-luc")
    @Operation(summary = "Tìm kiếm dân tộc theo tên châu lục", parameters = {
            @Parameter(name = "tenChauLuc", description = "Tên châu lục cần tìm", required =  false)
    })
    
    public ResponseEntity<List<DanhSachDanTocDto>> findDanTocByNameChauLuc(@PathParam(value = "tenChauLuc")String tenChauLuc){
        return ResponseEntity.ok(service.findByNameChauLuc(tenChauLuc));
    }
}
