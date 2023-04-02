package com.example.firstproject2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /*
     * TODO:
     * 1. Primitive and Wrapper class in Java
     * 2. One of the main difference of them is the default values 
     * so why this exception occurred?
     * -> 3. https://www.baeldung.com/hibernate-detached-entity-passed-to-persist
     */
    @JsonProperty(access = Access.READ_ONLY)
    private Long id;
    
    private String name;
    
    private String email;
}
