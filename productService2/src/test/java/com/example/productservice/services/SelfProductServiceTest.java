package com.example.productservice.services;

import com.example.productservice.clients.authenticationclient.dtos.UserDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static  org.mockito.Mockito.*;


@SpringBootTest
class SelfProductServiceTest {
//    @MockBean
//    private ProductRepository productRepository;

    @Autowired
    private SelfProductService productService;

//    @Test
//    void testGetSingleProductThrowsExceptionWhenNoSuchProduct() {
//        when(productRepository.findProductById(any())).thenReturn(null); //create
////        when(productRepository.findProductById(any())).thenCallRealMethod();
//
//        assertThrows(NotFoundException.class, () -> productService.getSingleProduct(1L)); // call and check
////      productService.getSingleProduct(1L);
//    }

//    @Test
//    void testGetUserById() {
//        UserDto answer = productService.getUserById(1l);
//
//    }

}