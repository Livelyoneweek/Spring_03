package com.example.week05springcore.controller;


import com.example.week05springcore.dto.RestaurantRequestDto;
import com.example.week05springcore.model.Restaurant;
import com.example.week05springcore.repository.RestaurantRepository;
import com.example.week05springcore.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant (@RequestBody RestaurantRequestDto restaurantRequestDto) throws Exception {

        restaurantService.registerRestaurant(restaurantRequestDto);
        Restaurant restaurant = new Restaurant(restaurantRequestDto);
        return restaurantRepository.save(restaurant);
    }

    //음식점 전체 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }


}
