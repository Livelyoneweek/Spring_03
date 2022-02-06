package com.example.week05springcore.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;


    public OrderDto (String restaurantName, List<FoodOrderDto> foods, int deliveryFee, int totalPrice){
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }


}
