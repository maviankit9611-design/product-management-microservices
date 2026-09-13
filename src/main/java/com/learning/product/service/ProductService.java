package com.learning.product.service;

import com.learning.product.dto.ProductResponse;
import com.learning.product.exception.ProductNotFound;
import com.learning.product.model.Products;
import com.learning.product.repository.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductRepo productRepo;
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public ResponseEntity<ProductResponse> addProduct(ProductResponse productResponse) {
        log.info("addProduct");
        Products product = new Products(productResponse.getProductName(),productResponse.getProductDescription(),
                productResponse.getProductPrice(),productResponse.getProductQuantity());
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }
    public ResponseEntity<ProductResponse> updateProduct(Long id,ProductResponse productResponse) {
       log.info("updateProduct");
       Products product = productRepo.findById(id).
               orElseThrow(()-> new ProductNotFound("Product not found"));
        product = new Products(productResponse.getProductName(),productResponse.getProductDescription(),
                productResponse.getProductPrice(),productResponse.getProductQuantity());
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    public ResponseEntity<?> deleteProduct(Long id) {
        log.info("deleteProduct");
        productRepo.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(id);

    }

    public ResponseEntity<ProductResponse> getProductById(Long id) {
        log.info("getProductById");
        Products product= productRepo.findById(id).
                orElseThrow(()-> new ProductNotFound("Product not found"));
        ProductResponse productResponse = new ProductResponse();
        productResponse =mapToResponse(product);
        return ResponseEntity.ok(productResponse);
    }

    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("getAllProducts");
        List<Products> products = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Products product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse =mapToResponse(product);
            productResponseList.add(productResponse);
        }
        return ResponseEntity.ok(productResponseList);
    }
    private ProductResponse mapToResponse(Products product) {

        return new ProductResponse(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity()
        );

    }
}

