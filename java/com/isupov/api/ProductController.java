package com.isupov.api;

import com.isupov.dto.Cart;
import com.isupov.dto.ProductDto;
import com.isupov.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService service;
    private Cart cart;

    public ProductController(ProductService service, Cart cart) {
        this.service = service;
        this.cart = cart;
    }



    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part",required = false) String titlePart) {
        if(page < 1){
            page = 1;
        }
        return service.getAllProduct(minPrice, maxPrice, titlePart, page).map(s -> new ProductDto(s));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return new ProductDto(service.getProductById(id));
    }


    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        productDto.setId(null);
        return new ProductDto(service.addProduct(productDto));
    }

    @PutMapping
    public ProductDto updateProductById(@RequestBody ProductDto productDto){
        return new ProductDto(service.updateProduct(productDto));
    }

@DeleteMapping("/{id}")
private void deleteProductById(@PathVariable Long id){
    service.deleteById(id);
}

    @GetMapping("/addToCart/{id}")
    public List<ProductDto> addProductToCart(@PathVariable Long id) {
        cart.getProductsList().add(new ProductDto(service.getProductById(id)));
        return cart.getProductsList();
    }
    @GetMapping("/deleteFromCart/{id}")
    public List<ProductDto> deleteProductFromCart(@PathVariable Long id) {
        for (ProductDto productDto : cart.getProductsList()) {
            if(productDto.getId() == id){
                cart.getProductsList().remove(productDto);
                return cart.getProductsList();
            }
        }
        return cart.getProductsList();
    }
}
