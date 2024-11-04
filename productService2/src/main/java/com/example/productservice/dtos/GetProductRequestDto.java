package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductRequestDto {
    private int noOfProducts;
    private int offset;
}
