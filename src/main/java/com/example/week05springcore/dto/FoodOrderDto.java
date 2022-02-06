package com.example.week05springcore.dto;

import lombok.Getter;


@Getter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;


    public FoodOrderDto (String name, int quantity, int price){
        this.name = name;
        this.quantity = quantity;
        this.price =price;
    }

}
