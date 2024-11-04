package com.example.productservice.services;


import com.example.productservice.clients.authenticationclient.dtos.UserDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.SearchProductRequestDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service(value = "selfProductService")
@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private RestTemplate restTemplate;


    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.restTemplate = new RestTemplate();

    }

    // this version is for getting user using service discovery
    @Override
    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
            System.out.println("In product service");

        ResponseEntity<UserDto> userDto = restTemplate.getForEntity(
//            "http://userservice/users/1",
                "http://userService/users/1",
            UserDto.class);
//        Product product = productRepository.findProductById(productId);
//
//        if (product == null) {
//            throw new NotFoundException("Product Doesn't Exist");
//        }
//
        return Optional.of(null);
    }

//    @Override
//    public UserDto getUserById(Long id) {
//        System.out.println("In product service");
//
//        ResponseEntity<UserDto> userDto = restTemplate.getForEntity(
//                "http://userService/users/1",
//                UserDto.class);
//
//
//        return userDto.getBody();
//    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProducts(int noOfProducts,int offset ){

        System.out.println(noOfProducts+ ","+ offset);


        int pageNumber = offset / noOfProducts;
        Pageable pageable =
                PageRequest.of(pageNumber, noOfProducts);

        // Create a Sort object that sorts by price descending and then by title ascending
//        Sort sort = Sort.by(Sort.Order.desc("price"), Sort.Order.asc("title"));
//        Sort sort2 = Sort.by(Sort.Order.desc("price"));
        Sort sort3 = Sort.by("price").descending().and(Sort.by("title").ascending());
        Pageable sortedPageable = PageRequest.of(pageNumber, noOfProducts , sort3);

        Page<Product> products1 = productRepository.findAll(sortedPageable);

        return products1;
    };

    @Override
    public Page<Product> searchProductsByTitleContaining(SearchProductRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNo(), requestDto.getPageSize());
        return productRepository.findByTitleContainingIgnoreCase(requestDto.getTitle(), pageable);
    }


//    @Override
//    public Optional<Product> getSingleProduct(Long productId) throws NotFoundException {
//        Product product = productRepository.findProductById(productId);
//
//        if (product == null) {
//            throw new NotFoundException("Product Doesn't Exist");
//        }
//
//        return Optional.of(product);
//    }

    @Override
    public Product addNewProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
