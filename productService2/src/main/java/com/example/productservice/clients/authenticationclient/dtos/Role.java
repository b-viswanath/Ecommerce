package com.example.productservice.clients.authenticationclient.dtos;


import com.example.productservice.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String name;

//    private List<User> users
}
