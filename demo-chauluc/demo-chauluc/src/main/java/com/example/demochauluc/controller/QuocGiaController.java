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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.Exception.InvalidInputException;
import com.example.demochauluc.Exception.ResponseObject;
import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.resource.QuocGiaResource;
import com.example.demochauluc.service.QuocGiaService;
import com.example.demochauluc.utils.Paging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/api/quocgia")
public class QuocGiaController {


    @Autowired
    private QuocGiaService service;
    
    
    @GetMapping
    @Operation(summary = "Lấy tất cả danh sách quốc gia, link tới GetAllPaging và getById")
    public ResponseEntity<CollectionModel<QuocGiaResource>> getAllQuocGia(){
        List<QuocGiaDto> dtos = service.getAllQuocGia();
        List<QuocGiaResource> resources = dtos.stream().map(QuocGiaResource::new).collect(Collectors.toList());
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuocGiaController.class).getAllQuocGia()).withSelfRel();
        CollectionModel<QuocGiaResource> collectionModel = CollectionModel.of(resources, link);
        return ResponseEntity.ok(collectionModel);
        
    }
    
    
    @GetMapping("/getAll")
    @Operation(summary = "Danh sách quốc gia theo trang")
    public ResponseEntity<Paging<QuocGiaDto>> getAllQuocGiaPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE)Pageable pageable){
        return ResponseEntity.ok().body(service.getAllQuocGiaPaging(pageable));
    }
    
    
    @GetMapping("/{id}")
    @Operation(summary = "Danh sách quốc gia theo id", parameters = {
            @Parameter(name = "id", description = "id quốc gia cần lấy",required = false)
    })
    public ResponseEntity<QuocGiaDto> getQuocGiaById(@PathVariable(name = "id",required = true)long id){
        QuocGiaDto quocgia = service.getQuocGiaById(id);
        if(quocgia == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(quocgia);
        }
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Tạo mới quốc gia")
    public ResponseEntity<ResponseObject> createQuocGia(@RequestBody @Valid QuocGiaDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        QuocGiaDto quocgias = service.createQuocGia(dto);
        return ResponseEntity.ok(new ResponseObject(quocgias,"success"));
    }
    
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Chỉnh sửa quốc gia theo id", parameters = {
            @Parameter(name = "id", description = "id quốc gia cần chỉnh sửa", required = false)
    })
    public ResponseEntity<ResponseObject> updateQuocGia(@PathVariable(name = "id",required = true)long id,@RequestBody @Valid QuocGiaDto dto,BindingResult result){
       if(result.hasErrors()) {
           throw new InvalidInputException(result);
       }
       dto.setId(id);
       boolean success = service.updateQuocGia(dto);
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa quốc gia theo id", parameters = {
            @Parameter(name = "id", description = "id quốc gia cần xóa", required = false)
    })
    public ResponseEntity<QuocGiaDto> removeQuocGia(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteQuocGia(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    
    
    @GetMapping("/tim-theo-ten-chau-luc")
    @Operation(summary = "Tìm quốc gia theo tên châu luc", parameters = {
            @Parameter(name = "tenChauLuc",description = "Tên châu lục mà quốc gia cần tìm kiếm ",required = false)
    })
    public ResponseEntity<Paging<DanhSachQuocGiaDto>> findQuocGiaByChauLuc(@PathParam(value = "tenChauLuc")String tenChauLuc,@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.findQuocGiaByChauLuc(tenChauLuc,pageable));
    }
}
