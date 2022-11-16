package com.example.deliveries.repository;

import com.example.deliveries.entity.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WareHouseRepository extends CrudRepository<Warehouse, Long> {

    @Query("select w.id, w.startTime, w.endTime from Warehouse as w " +
            "where w.endTime between :startTime and :endTime")
    List<Warehouse> findDeliveriesByBetweenTime(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);
}
