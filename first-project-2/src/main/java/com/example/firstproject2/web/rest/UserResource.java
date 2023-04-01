package com.example.firstproject2.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.firstproject2.dtos.UserDto;
import com.example.firstproject2.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserResource {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllCars() {
        return ResponseEntity.ok(userService.getAllUser());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getCarById(@PathVariable(name = "id", required = true) Long id) {
        UserDto dto = userService.getUserById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }
    
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createNewUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDto dto) {
        dto.setId(id);
        boolean success = userService.updateUser(dto);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable(name = "id", required = true) Long id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
