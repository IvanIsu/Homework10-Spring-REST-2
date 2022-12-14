package com.isupov.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class Cart {
    private List<ProductDto> productsList;

    @Autowired
    public Cart(){
        this.productsList = new ArrayList<>();
    }

}
