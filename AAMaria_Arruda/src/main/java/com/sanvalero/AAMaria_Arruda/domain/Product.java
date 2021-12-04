package com.sanvalero.AAMaria_Arruda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;
    @Column
    private String nameProduct;
    @Column
    private String descriptionProduct;
    @Column
    private String categoryProduct;
    @Column
    private float priceProduct;
    @Column
    private int quantityProduct;
    @Column
    private boolean stockProduct;
    @Column
    private LocalDateTime dateProduct;


}
