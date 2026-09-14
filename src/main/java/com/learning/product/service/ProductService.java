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
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final ProductRepo productRepo;
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Transactional
    public ProductResponse addProduct(ProductRequest request) {
        log.info("addProduct");
        Products product = new Products(request.getProductName(),request.getProductDescription(),
                request.getProductPrice(),request.getProductQuantity());
        return mapToResponse(productRepo.save(product));
    }
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest request) {
       log.info("updateProduct");
       Products product = productRepo.findById(id).
               orElseThrow(()-> new ProductNotFound("Product not found"));
        product.setName(request.getProductName());
        product.setDescription(request.getProductDescription());
        product.setPrice(request.getProductPrice());
        product.setQuantity(request.getProductQuantity());
        return mapToResponse(productRepo.save(product));
    }
    @Transactional
    public Long deleteProduct(Long id) {
        if (!productRepo.findById(id).isPresent()) {
            throw new ProductNotFound(
                    "Product not found with id: " + id);
        }
        log.info("deleteProduct");
        productRepo.deleteById(id);

        return id;

    }
    @Transactional(readOnly= true)
    public ProductResponse getProductById(Long id) {
        log.info("getProductById");
        Products product= productRepo.findById(id).
                orElseThrow(()-> new ProductNotFound("Product not found"));
        return mapToResponse(product);
    }
    @Transactional(readOnly= true)
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
    public Page<ProductResponse> searchProducts(
            String name,
            Pageable pageable) {

        log.info(
                "Searching products with name: {}, page: {}, size: {}",
                name,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<Products> productsPage =
                productRepo.findByNameStartingWithIgnoreCase(
                        name,
                        pageable
                );

        return productsPage.map(this::mapToResponse);
    }
}

