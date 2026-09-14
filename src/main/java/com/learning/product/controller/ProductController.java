package com.learning.product.controller;

import com.learning.product.dto.ProductRequest;
import com.learning.product.dto.ProductResponse;
import com.learning.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product APIs", description = "APIs for creating, retrieving, updating and deleting products")

@RestController
@RequestMapping("/api/products")
public class ProductController {
        Logger logger = LoggerFactory.getLogger(ProductController.class);
        private final ProductService productService;

        public ProductController(ProductService productService) {
                this.productService = productService;
        }

        @Operation(summary = "Get all products", description = "Returns a paginated and sortable list of products")
        @GetMapping
        public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
                logger.info("getAllProductsController");
                return ResponseEntity.ok(productService.getAllProducts(pageable));
        }

        @Operation(summary = "Get product by ID", description = "Returns a product for the supplied product ID")
        @GetMapping("/{id}")
        public ResponseEntity<ProductResponse> getProductsById(@PathVariable Long id) {
                logger.info("getProductsByIdController");
                return ResponseEntity.ok(productService.getProductById(id));
        }

        @Operation(summary = "Create product", description = "Creates a new product")
        @PostMapping
        public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
                logger.info("addProductController");
                return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(request));
        }

        @Operation(summary = "Update product", description = "Updates an existing product")
        @PutMapping("/{id}")
        public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                        @Valid @RequestBody ProductRequest request) {
                logger.info("updateProductController");
                return ResponseEntity.ok(productService.updateProduct(id, request));
        }

        @Operation(summary = "Delete product", description = "Deletes a product by ID")
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
                logger.info("deleteProductController");
                return ResponseEntity.ok(productService.deleteProduct(id));
        }

        @Operation(summary = "Search products", description = "Search products by name with pagination and sorting")

        @GetMapping("/search")
        public ResponseEntity<Page<ProductResponse>> searchProducts(
                        @RequestParam String name,
                        Pageable pageable) {

                logger.info("Searching products with name: {}", name);

                return ResponseEntity.ok(
                                productService.searchProducts(name, pageable));
        }

}
