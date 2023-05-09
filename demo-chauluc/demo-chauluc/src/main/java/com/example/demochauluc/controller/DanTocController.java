package com.example.demochauluc.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import com.example.demochauluc.utils.Paging;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/api/dantoc")
public class DanTocController {

    @Autowired
    private DanTocService service;
    
//    @GetMapping("/getAll/{pageNumber}/{pageSize}")
//    public ResponseEntity<Paging<DanTocDto>> getAllDanToc(@PathVariable int pageNumber,@PathVariable int pageSize){
//        return ResponseEntity.ok().body(service.getAllDanTocPaging(pageNumber, pageSize));
//    }
    
    @GetMapping("/getAll")
    public ResponseEntity<Paging<DanTocDto>> getAllDanTocPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllDanTocPaging(pageable));
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<DanTocDto> getDanTocById(@PathVariable(name = "id",required = true)long id){
        DanTocDto dantoc = service.getDanTocByID(id);
        if(dantoc == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(dantoc);
        }
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> createDanToc(@RequestBody @Valid DanTocDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        DanTocDto dantocs = service.createDanToc(dto);
        return ResponseEntity.ok(new ResponseObject(dantocs,"success"));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDanToc(@RequestBody @Valid DanTocDto dto,@PathVariable(name = "id",required = true)long id,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        dto.setId(id);
        boolean success = service.updateDanToc(dto);
        
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDanToc(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteDanToc(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    

    
    @GetMapping("/tim-kiem-theo-ten-quoc-gia")
    public ResponseEntity<List<DanhSachDanTocDto>> findNgonNguByNameQuocGia(@PathParam(value = "tenQuocGia")String tenQuocGia){
        return ResponseEntity.ok(service.findByNameQuocGia(tenQuocGia));
    }
    
    @GetMapping("/tim-kiem-theo-ten-chau-luc")
    public ResponseEntity<List<DanhSachDanTocDto>> findDanTocByNameChauLuc(@PathParam(value = "tenChauLuc")String tenChauLuc){
        return ResponseEntity.ok(service.findByNameChauLuc(tenChauLuc));
    }
}
