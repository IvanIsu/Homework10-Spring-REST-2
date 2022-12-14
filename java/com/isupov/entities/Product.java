package com.isupov.entities;

import com.isupov.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    public Product(ProductDto productDto){
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.price = productDto.getPrice();
    }
}
