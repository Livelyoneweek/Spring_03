package com.example.week05springcore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class foodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private Long restaurantId;

    @Column
    private Long foodId;

    @Column
    private int foodQuantity;

    public foodOrder (Long restaurantId, Long foodId, int foodQuantity ) {
        this.restaurantId = restaurantId;
        this.foodId= foodId;
        this.foodQuantity= foodQuantity;
    }


//    @Column(nullable = false)
//    private String restaurantName;
//
//    @Column(nullable = false)
//    private  List<FoodOrderDto> foods;
//
//    @Column(nullable = false)
//    private int deliveryFee;
//
//    @Column(nullable = false)
//    private int totalPrice;
//
//    public Order (String restaurantName, List<FoodOrderDto> foods, int deliveryFee, int totalPrice){
//        this.restaurantName=restaurantName;
//        this.foods=foods;
//        this.deliveryFee=deliveryFee;
//        this.totalPrice=totalPrice;
//    }


}
