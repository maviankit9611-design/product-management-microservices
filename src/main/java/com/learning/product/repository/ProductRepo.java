package com.learning.product.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.product.model.Products;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface ProductRepo
        extends JpaRepository<Products, Long> {
    Page<Products> findByNameStartingWithIgnoreCase(
            String name,
            Pageable pageable
    );

    @Modifying
    @Query("""
    UPDATE Products p
    SET p.quantity = p.quantity - :quantity
    WHERE p.id = :productId
      AND p.quantity >= :quantity
""")
    int reduceStock(
            @Param("productId") Long productId,
            @Param("quantity") Long quantity
    );
}
