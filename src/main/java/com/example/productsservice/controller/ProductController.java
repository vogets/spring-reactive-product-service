package com.example.productsservice.controller;


import com.example.productsservice.dto.ProductDTO;
import com.example.productsservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("getAllProducts")
    public Flux<ProductDTO> all()
    {
       return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable String id)
    {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDTO> createProduct(@RequestBody Mono<ProductDTO> productDTOMono)
    {
        return productService.createProduct(productDTOMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@PathVariable String id,@RequestBody Mono<ProductDTO> productDTOMono)
    {
         return productService.updateProduct(id,productDTOMono).map(ResponseEntity::ok)
         .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id)
    {
        return productService.deleteProduct(id);
    }

    @GetMapping
    public Flux<ProductDTO> getProductsInRange(@RequestParam("min") Double min,
                                               @RequestParam("max") Double max)
    {
        return productService.getProductsInRange(min,max);
    }




}
