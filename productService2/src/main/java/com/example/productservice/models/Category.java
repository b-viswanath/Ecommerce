package com.example.productservice.models;

//import jakarta.persistence.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Getter
@Setter
@Entity
@ToString(exclude = "products")
//@Table(name = "viswa")
public class Category extends BaseModel {
    private String name;
//    @Column
    private String description;
//    private Dummy dummy;

    // C : P
    // 1 : m
    // 1 : 1
    // 1 : m

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @Fetch(FetchMode.SUBSELECT)
//    @BatchSize(size = 1)
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Product> products;

//    @OneToOne
//    @OneToMany
//    @ManyToOne
//    @ManyToMany

}

// FetchModes allow to solve how a associated object is fetched
// Spring Data JPA modifies the behaviour of FetchModes a lot