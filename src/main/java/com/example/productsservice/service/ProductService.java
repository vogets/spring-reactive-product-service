package com.example.productsservice.service;

import com.example.productsservice.dto.ProductDTO;
import com.example.productsservice.entities.Product;
import com.example.productsservice.repositories.ProductRepository;
import com.example.productsservice.utilities.EntityDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository)
    {
        this.productRepository=productRepository;
    }

    public Flux<ProductDTO> getAllProducts()
    {
        return productRepository.findAll().map(EntityDTOUtil::toDTO);
    }

    public Mono<ProductDTO> getProductById(String id)
    {
        return productRepository.findById(id).map(EntityDTOUtil::toDTO);
    }

    public Mono<ProductDTO> createProduct(Mono<ProductDTO> productDTO)
    {
        return productDTO.map(EntityDTOUtil::toEntity)
                         .flatMap(productRepository::insert)
                         .map(EntityDTOUtil::toDTO);
    }

    public Mono<ProductDTO> updateProduct(String id,Mono<ProductDTO> productDTO)
    {
        return productRepository.findById(id)
                .flatMap(p->productDTO.map(EntityDTOUtil::toEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(productRepository::save)
                .map(EntityDTOUtil::toDTO);

    }

    public Mono<Void> deleteProduct(String id)
    {
        return productRepository.deleteById(id);

    }

    public Flux<ProductDTO> getProductsInRange(Double min,Double max)
    {
        return productRepository.findByPriceBetween(Range.closed(min,max))
                .map(EntityDTOUtil::toDTO);
    }
}
