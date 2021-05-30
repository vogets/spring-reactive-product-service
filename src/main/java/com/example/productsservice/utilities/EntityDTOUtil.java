package com.example.productsservice.utilities;

import com.example.productsservice.dto.ProductDTO;
import com.example.productsservice.entities.Product;

public class EntityDTOUtil {

    public static ProductDTO toDTO(Product product)
    {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO)
    {
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
