package com.isupov.service;

import com.isupov.dto.ProductDto;
import com.isupov.entities.Product;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataBaseFiller {

    private ProductService productService;

    public DataBaseFiller(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBaseApplication() {


        productService.addProduct(createProduct("Orange", 20));
        productService.addProduct(createProduct("Apple", 150));
        productService.addProduct(createProduct("Avocado", 20));
        productService.addProduct(createProduct("Apricot", 25));
        productService.addProduct(createProduct("Pear", 55));
        productService.addProduct(createProduct("Pineapple", 48));
        productService.addProduct(createProduct("Lime", 49));
        productService.addProduct(createProduct("Mango", 89));
        productService.addProduct(createProduct("Fig", 99));
        productService.addProduct(createProduct("Kiwi", 200));
        productService.addProduct(createProduct("Papaya", 10));
        productService.addProduct(createProduct("Potato", 5));
        productService.addProduct(createProduct("Tomato", 15));
        productService.addProduct(createProduct("Carrot", 25));
        productService.addProduct(createProduct("Cabbage", 13));
        productService.addProduct(createProduct("Pepper", 55));
        productService.addProduct(createProduct("Eggplant", 44));
        productService.addProduct(createProduct("Onion", 68));
        productService.addProduct(createProduct("Garlic", 74));
        productService.addProduct(createProduct("Cucumber", 250));

    }


    public ProductDto createProduct(String title, int price) {
        ProductDto product = new ProductDto();
        product.setTitle(title);
        product.setPrice(price);
        return product;
    }
}
