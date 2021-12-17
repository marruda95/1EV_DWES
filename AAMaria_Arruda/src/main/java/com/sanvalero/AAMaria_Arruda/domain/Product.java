package com.sanvalero.AAMaria_Arruda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long id;
    @Column
    @NonNull
    private String name;
    @Column
    @NonNull
    private String description;
    @Column
    @NonNull
    private String category;
    @Column
    @NonNull
    private float price;
    @Column
    @NonNull
    private int quantity;
    @Column
    @NonNull
    private boolean stock;
    @Column
    @NonNull
    private LocalDateTime date;


}
