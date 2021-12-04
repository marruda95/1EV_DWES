package com.sanvalero.AAMaria_Arruda.service;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() { return productRepository.findAll(); }
}
