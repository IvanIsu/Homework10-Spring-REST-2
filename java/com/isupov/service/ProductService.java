package com.isupov.service;


import com.isupov.dto.Cart;
import com.isupov.dto.ProductDto;
import com.isupov.entities.Product;
import com.isupov.repository.ProductRepository;
import com.isupov.repository.specfication.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class ProductService {

    private ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }


    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
       return productRepository.save(product);
    }

    public Page<Product> getAllProduct(Integer minPrice, Integer maxPrice, String titlePart, Integer page) {

        Specification<Product> spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecification.titleLike(titlePart));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }
    @Transactional
    public Product updateProduct(ProductDto productDto) {
        Product product = new Product(productDto);
            productRepository.save(product);
//        Product productFormDb = productRepository.findById(product.getId()).orElseThrow();
//
//        if(product.getTitle() != null){
//            productFormDb.setTitle(product.getTitle());
//        }
//        if (product.getPrice() != null){
//            productFormDb.setPrice(product.getPrice());
//        }
        return product;
    }


}
