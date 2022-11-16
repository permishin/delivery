package com.example.deliveries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Embeddable
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    private Double weight;
    private Double price;
    @Transient
    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;
}
