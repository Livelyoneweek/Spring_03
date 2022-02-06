package com.example.week05springcore.model;

import com.example.week05springcore.dto.FoodDto;
import com.example.week05springcore.dto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column
    private Long restaurantId;


    public Food(FoodDto foodDto,Long restaurantId) {
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
        this.restaurantId = restaurantId;
    }

    public Food(String name, int price ,Long restaurantId) {
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
    }






}
