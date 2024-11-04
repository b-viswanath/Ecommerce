package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories // Ensure this annotation is correctly configured
//@EntityScan(basePackages = {"com.example.productservice.inheritanceexamples.mappedsuperclass"})
public class ProductService2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductService2Application.class, args);
    }

}
