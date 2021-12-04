package com.sanvalero.AAMaria_Arruda.service;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() { return productRepository.findAll(); }

    @Override
    public List<Product> findByCategory(String categoryProduct){
        List<Product> products = productRepository.findByCategory(categoryProduct);
        return products;
    }
}
