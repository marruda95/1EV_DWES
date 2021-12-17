package com.sanvalero.AAMaria_Arruda.service;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findByCategory(String category);
    Product findProduct(long id) throws ProductNotFoundException;
}
