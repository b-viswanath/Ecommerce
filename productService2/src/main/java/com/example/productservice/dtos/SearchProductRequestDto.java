package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductRequestDto {
//    @NotBlank(message = "Title cannot be blank")
    private String title;

//    @Min(value = 0, message = "Page number must be zero or positive")
    private int pageNo = 0; // default value

//    @Min(value = 1, message = "Page size must be greater than zero")
    private int pageSize = 10; // default value
}
