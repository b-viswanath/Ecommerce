package com.example.productservice.clients.fakestoreapi;


import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        return Arrays.asList(l.getBody());
    }

    Optional<FakeStoreProductDto> getSingleProduct(Long productId) throws NotFoundException {
        return null;
    }

    FakeStoreProductDto addNewProduct(ProductDto product) {
        return null;
    }

    /*
    Product object has only those fields filled which need to be updated.
    Everything else is null
     */
    FakeStoreProductDto updateProduct(Long productId, Product product) {
        return null;
    }
    // if (product.getImageUrl() != null) {
    //
    // }
    FakeStoreProductDto replaceProduct(Long productId, Product product) {
        return null;
    }

    FakeStoreProductDto deleteProduct(Long productId) {
        return null;
    }

}
