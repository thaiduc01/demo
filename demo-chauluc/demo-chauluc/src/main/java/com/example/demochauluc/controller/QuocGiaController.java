package com.example.demochauluc.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.Exception.InvalidInputException;
import com.example.demochauluc.Exception.ResponseObject;
import com.example.demochauluc.Service.QuocGiaService;
import com.example.demochauluc.dtos.DanhSachQuocGiaDto;
import com.example.demochauluc.dtos.QuocGiaDto;
import com.example.demochauluc.utils.Paging;


@RestController
@RequestMapping("/api/quocgia")
public class QuocGiaController {


    @Autowired
    private QuocGiaService service;
    
    @GetMapping("/getAll")
    public ResponseEntity<Paging<QuocGiaDto>> getAllQuocGia(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE)Pageable pageable){
        return ResponseEntity.ok().body(service.getAllQuocGiaPaging(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<QuocGiaDto> getQuocGiaById(@PathVariable(name = "id",required = true)long id){
        QuocGiaDto quocgia = service.getQuocGiaById(id);
        if(quocgia == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(quocgia);
        }
    }
    
    @PostMapping
    public ResponseEntity<ResponseObject> createQuocGia(@RequestBody @Valid QuocGiaDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        QuocGiaDto quocgias = service.createQuocGia(dto);
        return ResponseEntity.ok(new ResponseObject(quocgias,"success"));
    }
    
    
   @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateQuocGia(@PathVariable(name = "id",required = true)long id,@RequestBody @Valid QuocGiaDto dto,BindingResult result){
       if(result.hasErrors()) {
           throw new InvalidInputException(result);
       }
       dto.setId(id);
       boolean success = service.updateQuocGia(dto);
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<QuocGiaDto> removeQuocGia(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteQuocGia(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/tim-theo-ten-chau-luc")
    public ResponseEntity<Paging<DanhSachQuocGiaDto>> findQuocGiaByChauLuc(@PathParam(value = "tenChauLuc")String tenChauLuc,@ParameterObject Pageable pageable){
        return ResponseEntity.ok().body(service.findQuocGiaByChauLuc(tenChauLuc,pageable));
//        Optional<Paging<DanhSachQuocGiaDto>> optionalResult = Optional.ofNullable(service.findQuocGiaByChauLuc(tenChauLuc, pageable));
//        if (optionalResult.isPresent()) {
//            return ResponseEntity.ok().body(optionalResult.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }
}
