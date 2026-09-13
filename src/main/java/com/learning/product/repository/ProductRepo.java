package com.learning.product.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.product.model.Products;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepo
        extends JpaRepository<Products, Long> {

}
