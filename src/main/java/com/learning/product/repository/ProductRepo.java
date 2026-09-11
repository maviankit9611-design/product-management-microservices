package com.learning.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.product.model.Products;

public interface ProductRepo
        extends JpaRepository<Products, Long> {

}
