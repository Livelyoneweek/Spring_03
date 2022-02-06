package com.example.week05springcore.repository;

import com.example.week05springcore.model.foodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodOrderRepository extends JpaRepository<foodOrder, Long> {
    List<foodOrder> findAllBy();

}
