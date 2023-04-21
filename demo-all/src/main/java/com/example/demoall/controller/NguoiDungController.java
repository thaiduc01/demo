package com.example.demoall.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoall.dtos.NguoidungDto;
import com.example.demoall.entity.NguoiDung;
import com.example.demoall.service.NguoiDungService;

@RestController
@RequestMapping("/nguoidung")
public class NguoiDungController {

    @Autowired
    private NguoiDungService service;
    
    
    @GetMapping
    public ResponseEntity<List<NguoidungDto>> getAllNguoiDung(){
        return ResponseEntity.ok(service.getAllNguoiDung());
    }
    
    //phân trang, pageNumber là số trang, pageSize là số người dùng trong trang.
    @GetMapping("/paging/{pageNumber}/{pageSize}")
    public ResponseEntity<List<NguoiDung>> getNguoiDungPaging(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<NguoiDung> data = service.getNguoiDungPagination(pageNumber, pageSize);
        return ResponseEntity.ok(data.getContent());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NguoidungDto> getNguoidungById(@PathVariable(name = "id",required = true)Long id){
        NguoidungDto dto = service.getNguoiDungById(id);
        if(dto == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNguoiDung(@PathVariable(name = "id",required = true)Long id,@RequestBody NguoidungDto dto){
        dto.setId(id);
        boolean success = service.updateNguoiDung(dto);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<NguoidungDto> createNewNguoiDung(@RequestBody @Valid NguoidungDto dto){
        return ResponseEntity.ok(service.createNewNguoidung(dto));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNguoiDung(@PathVariable(name = "id")Long id){
        boolean success = service.deleteNguoiDung(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // tìm kiếm người dùng bằng tên theo querydsl
    
    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<NguoiDung>> findNguoiDungByName(@PathVariable(name = "name")String name){
        return ResponseEntity.ok(service.findNguoiDungByName(name));
    }
    
}
