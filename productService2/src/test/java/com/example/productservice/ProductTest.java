package com.example.productservice;


import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductDBDTo;
import com.example.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTest {
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//////
//////    private SelfProductService selfProductService;
//////
//    @Test
//    void savingProductsAndCategory() {
////        Category category = new Category();
////        category.setName("phones");
////        Category savedCategory = categoryRepository.save(category);
////
////        Product product = new Product();
////        product.setPrice(100);
////        product.setImageUrl("phone1");
////        product.setCategory(category);
////        productRepository.save(product);
//
////        Category category = new Category();
////        category.setName("electronics");
////        Category savedCategory = categoryRepository.save(category);
////
////        Product product = new Product();
////        product.setPrice(200);
////        product.setImageUrl("ele1");
////        product.setCategory(category);
////        productRepository.save(product);
//    }
//
//    @Test
//    @Transactional
//    void fetchTypesTest() {
//        Product product = productRepository.findProductById(2L);
//
//        System.out.println("Fetched product");
//
//        Category category = product.getCategory();
//        String name = category.getName();
//        System.out.println("Category of Fetched product: " + name);
//    }
////
//    @Test
//    void deleteProduct() {
//        productRepository.deleteById(3L);
//    }
//    @Test
//    void deleteCategory() {
//        categoryRepository.deleteById(5L);
//    }
////
//    @Test
//    @Transactional
////    @Rollback(value = false)
//    @Commit()
//    void saveProductsForCategory() {
////        Category category = categoryRepository.findById(6L).get();
////
////
////        Product product = new Product();
////        product.setPrice(101);
////        product.setImageUrl("phone1");
////        product.setCategory(category);
////        Product savedProduct = productRepository.save(product);
////
////        product = new Product();
////        product.setPrice(102);
////        product.setImageUrl("phone2");
////        product.setCategory(category);
////        productRepository.save(product);
////
//////        Category category1 = new Category();
//////        category.setName("hello");
//////        categoryRepository.save(category1);
////        product = new Product();
////        product.setPrice(103);
////        product.setImageUrl("phone3");
////        product.setCategory(category);
////        productRepository.save(product);
//
//        Category category = categoryRepository.findById(7L).get();
//
//
//        Product product = new Product();
//        product.setPrice(201);
//        product.setImageUrl("ele1");
//        product.setCategory(category);
//        Product savedProduct = productRepository.save(product);
//
//        product = new Product();
//        product.setPrice(202);
//        product.setImageUrl("ele2");
//        product.setCategory(category);
//        productRepository.save(product);
//
//        product = new Product();
//        product.setPrice(203);
//        product.setImageUrl("ele3");
//        product.setCategory(category);
//        productRepository.save(product);
//
//    }

//    @Test
//    @Transactional
////    @Rollback(value = false)
//    @Commit()
//    void getCetegoryUsingLazyAndEagerProductTypes() {
//        List<Category> categoryList = categoryRepository.findAllByIdIn(List.of(1l,3l));
//        for (Category category: categoryList) {
//            System.out.println(category);
//        }
//
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void getProductsForCategory() throws InterruptedException {
////        Category category = categoryRepository.findById(2L).get();
//
//        List<Category> categories = categoryRepository.findAllByIdIn(List.of(6L, 7L)); // select * from categories where cateory in 1, 2
//
//        // select * from categories where id in (2, 52);
////        Thread.sleep(100L);
//        //System.out.println("Doing something");
////        Thread.sleep(100L);
//        // select * from products where category_id = 2;
//        // select * from products where category_id = 52;
//
//        for (Category category: categories) {
//            for (Product product : category.getProducts()) {
//
//                System.out.println(product.getImageUrl());
//            }
//        }
//
//        //System.out.println("Done something");
//    }
//
//    @Test
//    @Transactional
//    void getProductsFor1Category() throws InterruptedException {
////        Category category = categoryRepository.findById(2L).get();
//
//        Category category = categoryRepository.findById(2L).get(); // select * from categories where cateory in 1, 2
//
//        // select * from categories where id in (2, 52);
//        Thread.sleep(100L);
//        System.out.println("Doing something");
//        Thread.sleep(100L);
//        // select * from products where category_id = 2;
//        // select * from products where category_id = 52;
//
//        for (Product product : category.getProducts()) {
//            System.out.println(product.getPrice());
//        }
//    }
//
//    @Test
//    void checkWorkingFine() {
//
//        List<Product> products = selfProductService.getAllProducts();
//    }
//
//    @Test
//     public void getProductsByCategoryNameAndId(){
//        List<Product> products = productRepository.findProductsByCategory_Name("phones");
//        for (Product pro : products) {
//            System.out.println(pro);
//        }
//    }
//    @Test
//    public void demonstrateCustomQueries() {
//        //List<ProductDBDTo> productDBDTos = productRepository.laaoProductsWithId(6l);
//        //List<Product> products = productRepository.getByIdAndTitle(1L, "hi");
//        //List<Product> productDBDTos = productRepository.laaoProductsWithId(6l);
//        ProductDBDTo productDBDTos = productRepository.laaoProductsWithId(6l);
//        System.out.println(productDBDTos.getTitle());
//
//
//    }
}
