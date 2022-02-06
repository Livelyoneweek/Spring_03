package com.example.week05springcore.controller;

import com.example.week05springcore.model.Food;
import com.example.week05springcore.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@RequestBody List<Map<String, Object>>listMap , Errors Errors, @PathVariable Long restaurantId) {
        // 등록 전 중복으로 상품 등록한 것을 찾기 위한 알고리즘
        // 빈 배열에 음식 이릉 다 담는중
        String[] arr = new String[listMap.size()];
        int z =0;
        for (Map<String, Object> map : listMap) {
            String name = (String) map.get("name");
            arr[z]=name;
            z +=1;
        }
        // 배열에 이름으로 중복 상품 찾고있음
        for (int i = 0; i<listMap.size(); i++) {
            for (int j =i+1; j<listMap.size(); j++){
                if(arr[i].equals(arr[j])){
                    throw new IllegalArgumentException("중복 상품이 존재합니다.");
                }
            }
        }

        //사전 검색으로 중복 검열 후 이제 유효성 검사 및 등록 시작
        for (Map<String, Object> map : listMap) {
            System.out.println("name : " + map.get("name"));
            System.out.println("price : " + map.get("price"));
            System.out.println(restaurantId);

            String name = (String) map.get("name");
            int price = (int) map.get("price");

            if(price<1000 || price>1000000) {
                throw new IllegalArgumentException("음식 가격은 1000~100,0000원 입니다.");
            }
            if(price%100!=0) {
                throw new IllegalArgumentException("음식 가격은 100원 단위로 해주시기 바랍니다.");
            }
            if(price%100!=0) {
                throw new IllegalArgumentException("음식 가격은 100원 단위로 해주시기 바랍니다.");
            }
            if(!foodRepository.findAllByRestaurantIdAndName(restaurantId,name).isEmpty()) {
                throw new IllegalArgumentException("중복 상품이 존재합니다.");
            }
            Food food = new Food(name, price ,restaurantId);
            foodRepository.save(food);
        }
    }
    //음식점에 음식 전체 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoods(@PathVariable Long restaurantId) {
        return foodRepository.findAllByrestaurantId(restaurantId);
    }


}




