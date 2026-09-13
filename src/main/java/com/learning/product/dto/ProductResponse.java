package com.learning.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component 
public class ProductResponse {

    @NotBlank (message ="Product name cannot be blank")
    private String productName;
    @NotBlank (message ="Description cannot be blank")
    private String productDescription;
    @Positive (message="Price cannot be zero or negative")
    private BigDecimal productPrice;
    @PositiveOrZero ( message ="Quantity cannot be zero")
    private BigDecimal productQuantity;

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public BigDecimal getProductQuantity() {
        return productQuantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(BigDecimal productQuantity) {
        this.productQuantity = productQuantity;
    }

    public ProductResponse() {

    }
    public ProductResponse(String productName, String productDescription, BigDecimal productPrice, BigDecimal productQuantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
}
