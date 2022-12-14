package com.isupov.dto;

import com.isupov.entities.Product;

public class ProductDto {


    private Long id;
    private String title;
    private Integer price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductDto() {
    }
}
