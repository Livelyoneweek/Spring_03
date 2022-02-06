package com.example.week05springcore.repository;

import com.example.week05springcore.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByName(String name);
    List<Food> findAllByrestaurantId(Long restaurantId);
    List<Food> findAllByRestaurantIdAndName(Long restaurantId, String name);
    Optional<Food> findByRestaurantIdAndId(Long restaurantId, Long id);

}