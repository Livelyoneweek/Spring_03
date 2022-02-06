package com.example.week05springcore.service;

import com.example.week05springcore.dto.RestaurantRequestDto;
import com.example.week05springcore.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // 레스토랑등록할때 유효성 검사
    @Transactional
    public void registerRestaurant(RestaurantRequestDto restaurantRequestDto) throws Exception {

        String name = restaurantRequestDto.getName();
        int minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        int deliveryFee = restaurantRequestDto.getDeliveryFee();

        if(minOrderPrice<1000 || minOrderPrice>100000) {
            throw new IllegalArgumentException("최소 주문 가격은 1000~100,000원 입니다.");
        }

        if(minOrderPrice%100!=0) {
            throw new IllegalArgumentException("최소 주문 100원 단위로 해주시기 바랍니다.");
        }

        if(deliveryFee<0 || deliveryFee>10000) {
            throw new IllegalArgumentException("기본 배달비는 0~10000원으로 500원 단위입니다.");
        }
        if(deliveryFee%500!=0) {
            throw new IllegalArgumentException("기본 배달비는 0~10000원으로 500원 단위입니다.");
        }


    }

}
