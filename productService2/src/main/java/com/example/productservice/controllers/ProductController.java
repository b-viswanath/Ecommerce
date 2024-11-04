package com.example.productservice.controllers;


import org.springframework.beans.factory.annotation.Qualifier;
import com.example.productservice.clients.authenticationclient.AuthenticationClient;
import com.example.productservice.clients.authenticationclient.dtos.Role;
import com.example.productservice.clients.authenticationclient.dtos.SessionStatus;
import com.example.productservice.clients.authenticationclient.dtos.ValidatetokenResponseDto;
import com.example.productservice.dtos.GetProductRequestDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.dtos.SearchProductRequestDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ProductRepository productRepository;
    private AuthenticationClient authenticationClient;

    public ProductController(ProductService productService, ProductRepository productRepository, AuthenticationClient authenticationClient) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.authenticationClient = authenticationClient;
    }

    @GetMapping("/test")
    public  String test(){
        return "Hi welcome web application";
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getProducts(@RequestBody GetProductRequestDto request){
        Page<Product> products1 = productService.getProducts(request.getNoOfProducts(),request.getOffset());
        return new ResponseEntity<>(products1, HttpStatus.OK);
    }

    @GetMapping("/title_search")
    public ResponseEntity<Page<Product>> searchProducts(@RequestBody SearchProductRequestDto requestDto) {
        Page<Product> products = productService.searchProductsByTitleContaining(requestDto);
        return ResponseEntity.ok(products);
    }

    // Make only admins be able to access all products
    @GetMapping()
   // public ResponseEntity<List<Product>> getAllProducts() {
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token,
                                                        @Nullable @RequestHeader("USER_ID") Long userId) {
       // check if token exists
//        if (token == null || userId == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
////
//        ValidatetokenResponseDto response = authenticationClient.validate(token, userId);
//
//        // check if token is valid
//        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
////
////        // validate token
////        // RestTemplate rt = new RestTRemplate();
////        //  rt.get("localhost:9090/auth/validate?)
////
//        // check if user has permissions
//        boolean isUserAdmin = false;
//        for (Role role: response.getUserDto().getRoles()) {
//            if (role.getName().equals("ADMIN")) {
//                isUserAdmin = true;
//            }
//        }
////
//        if (!isUserAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<Product> products = productService.getAllProducts();

//        products.get(0).setPrice(100); /// Bug induced in my code
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add(
                "auth-token", "noaccess4uheyhey"
        );

//        try {
        Optional<Product> productOptional = productService.getSingleProduct(productId);
//        } catch (Exception e) {
//            throw e;
//        }
//
//        if (product == null) {
//
//        }

//        Product product = productOptional.get();

        if (productOptional.isEmpty()) {
            throw new NotFoundException("No Product with product id: " + productId);
        }

        ResponseEntity<Product> response = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );

        return response;
//        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
//        responseDto.setProduct(
//              return  ;
//        );

//        return responseDto;
    }


    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto product) {

//        Product newProduct = productService.addNewProduct(
//                product
//        );

        Product newProduct = new Product();
        newProduct.setDescription(product.getDescription());
        newProduct.setImageUrl(product.getImage());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());

        newProduct = productRepository.save(newProduct);

        ResponseEntity<Product> response = new ResponseEntity<>(newProduct, HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId,
                                 @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting a product with id: " + productId;
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorResponseDto> viswa(Exception exception) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
//        errorResponseDto.setErrorMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
//    }
}


// Ngrok
// Localtunnel