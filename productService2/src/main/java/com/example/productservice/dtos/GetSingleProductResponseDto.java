package com.example.productservice.dtos;

//import dev.naman.productservicettsevening.models.Category;
//import dev.naman.productservicettsevening.models.Product;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}
