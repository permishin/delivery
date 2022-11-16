package com.example.deliveries.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@Setter
@Table(name = "current_delivery")
public class CurrentDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;

    @NotNull
    private Status status;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_delivery", joinColumns = @JoinColumn(name = "delivery_id"))
    @MapKeyColumn(name = "product_Key")
    @Column(name = "count_Value")
    private Map<Product, Integer> productCount = new HashMap<>();
}
