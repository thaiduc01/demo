package com.example.demochauluc.controller;

import java.util.List;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demochauluc.Service.NgonNguService;
import com.example.demochauluc.dtos.DanhSachNgonNguDto;
import com.example.demochauluc.dtos.NgonNguDto;
import com.example.demochauluc.utils.Paging;

@RestController
@RequestMapping("/api/ngonngu")
public class NgonNguController {

    @Autowired
    private NgonNguService service;
    
    @GetMapping("/getAll")
    public ResponseEntity<Paging<NgonNguDto>> getAllNgonNgu(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllNgonNguPaging(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NgonNguDto> getNgonNguById(@PathVariable(name = "id",required = true)long id){
        NgonNguDto dto = service.getNgonNguByID(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(dto);
        }
    }
    
    @PostMapping
    public ResponseEntity<NgonNguDto> createNgonNgu(@RequestBody @Valid NgonNguDto dto){
        return ResponseEntity.ok(service.createNgonNgu(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNgonNgu(@PathVariable(name = "id",required = true)long id,@RequestBody NgonNguDto dto){
        dto.setId(id);
        boolean success = service.updateNgonNgu(dto);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNgonNgu(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteNgonNgu(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("tim-kiem-theo-ten-quoc-gia")
    public ResponseEntity<List<DanhSachNgonNguDto>> findNgonNguByNameQuocGia(@PathParam(value = "tenQuocGia")String tenQuocGia){
        return ResponseEntity.ok(service.findByNameQuocGia(tenQuocGia));
    }
}
