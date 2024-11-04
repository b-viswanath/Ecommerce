package com.example.productservice.repositories;

public interface Queries {
    //String LAAO_PRODUCTS_WITH_ID_QUERY = "select image_url as title from product where id = :id";
    String LAAO_PRODUCTS_WITH_ID_QUERY = "SELECT p.image_url AS title FROM product p WHERE p.id = :id";

}

