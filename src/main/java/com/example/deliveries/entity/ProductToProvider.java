package com.example.deliveries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product_to_provider")
public class ProductToProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "provider_id")
    private Provider providerId;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product productId;
}
