package com.programming.productservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@UuidGenerator
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
