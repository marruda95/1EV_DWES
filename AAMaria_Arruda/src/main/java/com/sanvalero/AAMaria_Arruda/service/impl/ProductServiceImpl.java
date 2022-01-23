package com.sanvalero.AAMaria_Arruda.service.impl;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import com.sanvalero.AAMaria_Arruda.exception.ProductNotFoundException;
import com.sanvalero.AAMaria_Arruda.repository.ProductRepository;
import com.sanvalero.AAMaria_Arruda.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAllProducts() {
            return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(String category){
        List<Product> products = productRepository.findByCategory(category);
        return products;
    }

    @Override
    public Product findProduct(long id) throws ProductNotFoundException{
        return productRepository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
    }

}
