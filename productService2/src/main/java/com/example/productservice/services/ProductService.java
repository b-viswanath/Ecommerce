package com.example.productservice.services;


import com.example.productservice.clients.authenticationclient.dtos.UserDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.SearchProductRequestDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Page<Product> getProducts(int noOfProducts,int offset);

    Optional<Product> getSingleProduct(Long productId) throws NotFoundException;

    Product addNewProduct(ProductDto product);
//    public UserDto getUserById(Long id) ;



        Page<Product> searchProductsByTitleContaining(SearchProductRequestDto requestDto);
    /*
        Product object has only those fields filled which need to be updated.
        Everything else is null
         */
    Product updateProduct(Long productId, Product product);
    // if (product.getImageUrl() != null) {
    //
    // }
    Product replaceProduct(Long productId, Product product);

    boolean deleteProduct(Long productId);
}

// update product with id 123
// {
//   name: iPhone 15
// }
