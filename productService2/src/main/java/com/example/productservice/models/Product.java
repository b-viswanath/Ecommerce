package com.example.productservice.models;

//import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    // P : C
    // 1 -> 1
    // m <- 1
    // M <-> 1

   // @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})

    //@ManyToOne
    private Category category;
    private String imageUrl;
//    private boolean isPublic;
}

// P -> C