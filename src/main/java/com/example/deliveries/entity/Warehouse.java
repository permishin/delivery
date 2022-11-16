package com.example.deliveries.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "warehouse_table", joinColumns = @JoinColumn(name = "delivery_id"))
    @MapKeyColumn(name = "product_Key")
    @Column(name = "count_Value")
    private Map<Product, Integer> warehouseProduct = new HashMap<>();

}
