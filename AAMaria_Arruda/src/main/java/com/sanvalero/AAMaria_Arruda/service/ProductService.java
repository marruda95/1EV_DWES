package com.sanvalero.AAMaria_Arruda.service;

import com.sanvalero.AAMaria_Arruda.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    List<Product> findByCategory(String categoryProduct);
}
