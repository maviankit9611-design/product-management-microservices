package com.learning.product.service;

import com.learning.product.dto.ProductRequest;
import com.learning.product.dto.ProductResponse;
import com.learning.product.exception.ProductNotFound;
import com.learning.product.model.Products;
import com.learning.product.repository.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductRepo productRepo;
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public ProductResponse addProduct(ProductRequest productResponse) {
        log.info("addProduct");
        Products product = new Products(productResponse.getProductName(),productResponse.getProductDescription(),
                productResponse.getProductPrice(),productResponse.getProductQuantity());
        return mapToResponse(productRepo.save(product));
    }
    public ProductResponse updateProduct(Long id, ProductRequest productResponse) {
       log.info("updateProduct");
       Products product = productRepo.findById(id).
               orElseThrow(()-> new ProductNotFound("Product not found"));
        product.setName(productResponse.getProductName());
        product.setDescription(productResponse.getProductDescription());
        product.setPrice(productResponse.getProductPrice());
        product.setQuantity(productResponse.getProductQuantity());
        return mapToResponse(productRepo.save(product));
    }

    public Long deleteProduct(Long id) {
        log.info("deleteProduct");
        productRepo.deleteById(id);

        return id;

    }

    public ProductResponse getProductById(Long id) {
        log.info("getProductById");
        Products product= productRepo.findById(id).
                orElseThrow(()-> new ProductNotFound("Product not found"));
        return mapToResponse(product);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        log.info("Fetching products. Page: {}, Size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize());
        Page<Products> productsPage = productRepo.findAll(pageable);
        return productsPage.map(this::mapToResponse);
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

