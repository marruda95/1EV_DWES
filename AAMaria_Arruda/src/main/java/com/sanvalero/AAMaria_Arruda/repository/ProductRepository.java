package com.sanvalero.AAMaria_Arruda.repository;

import com.sanvalero.AAMaria_Arruda.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findByName(String nameProduct);
    Product findByPrice(Float priceProduct);
    List<Product> findByCategory(String categoryProduct);
}
