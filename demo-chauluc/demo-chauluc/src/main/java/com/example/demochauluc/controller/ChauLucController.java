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
import com.example.demochauluc.Service.ChauLucService;
import com.example.demochauluc.dtos.ChauLucDto;
import com.example.demochauluc.resource.ChauLucResource;
import com.example.demochauluc.utils.Paging;

@RestController
@RequestMapping("/api/chauluc")
public class ChauLucController {

    @Autowired
    private ChauLucService service;
    
    @GetMapping
    public ResponseEntity<CollectionModel<ChauLucResource>> getAllChauLuc(){
        List<ChauLucDto> dtos = service.getAllChauLuc();
        List<ChauLucResource> resource = dtos.stream().map(ChauLucResource::new).collect(Collectors.toList());
        
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChauLucController.class).getAllChauLuc()).withSelfRel();
        CollectionModel<ChauLucResource> collectionModel = CollectionModel.of(resource,link);
        
        return ResponseEntity.ok(collectionModel);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<Paging<ChauLucDto>> getDSChauLucPaging(@ParameterObject @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return ResponseEntity.ok().body(service.getAllChauLucPaging(pageable));
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ChauLucDto> getChauLucById(@PathVariable(name = "id",required = true)long id){
        ChauLucDto chauluc = service.getChauLucById(id);
        if(chauluc == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(chauluc);
        }
    }
    
    
    @PostMapping
    public ResponseEntity<ResponseObject> createNewChauLuc(@RequestBody @Valid ChauLucDto dto,BindingResult result,HttpServletRequest request){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        ChauLucDto cl = service.createChauLuc(dto);
        return ResponseEntity.ok(new ResponseObject(cl,"success"));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateChauLuc(@PathVariable(name = "id",required = true)long id,@RequestBody @Valid ChauLucDto dto,BindingResult result){
        if(result.hasErrors()) {
            throw new InvalidInputException(result);
        }
        dto.setId(id);
        boolean success = service.updateChauLuc(dto);
        return ResponseEntity.ok(new ResponseObject(success,"success"));
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChauLuc(@PathVariable(name = "id",required = true)long id){
        boolean success = service.deleteChauLuc(id);
        if(success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();        
            }
    }
    
}
