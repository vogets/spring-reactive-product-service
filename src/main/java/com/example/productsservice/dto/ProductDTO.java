package com.example.productsservice.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
    private String id;
    private String description;
    private Double price;

}
