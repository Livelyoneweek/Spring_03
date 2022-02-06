package com.example.week05springcore.controller;

import com.example.week05springcore.dto.FoodOrderDto;
import com.example.week05springcore.dto.FoodOrderRequestDto;
import com.example.week05springcore.dto.OrderDto;
import com.example.week05springcore.dto.OrderRequestDto;
import com.example.week05springcore.model.foodOrder;

import com.example.week05springcore.repository.FoodRepository;
import com.example.week05springcore.repository.FoodOrderRepository;
import com.example.week05springcore.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodOrderRepository foodorderRepository;

    //음식 주문
    @PostMapping("/order/request")
    public OrderDto orderFood (@RequestBody OrderRequestDto orderRequestDto) {

        Long restaurantId = orderRequestDto.getRestaurantId(); //레스토랑 id
        List<FoodOrderRequestDto> foods = orderRequestDto.getFoods(); // 주문자의 음식 배열을 받음 각 음식 id랑 수량 들어있음

        int totalPrice = 0; //토탈 비용 변수 선언

        String restaurantName = restaurantRepository.findById(restaurantId).get().getName(); //레스토랑 id를 통한 레스토랑 이름 받음
        System.out.println(restaurantName);
        int minOrderPrice = restaurantRepository.findById(restaurantId).get().getMinOrderPrice(); //레스토랑 id를 통한 레스토랑 최소 배달비용을 받음
        int deliveryFee = restaurantRepository.findById(restaurantId).get().getDeliveryFee(); //레스토랑 id를 통한 레스토랑 배달비를 받음


        List<FoodOrderDto> orderList = new ArrayList<>(); // 음식 name, 수량, price*수량 한 가격이 든 객체 배열을 선언,리스폰할때 필요

        // 배달 최소 주문 가격 및 수량  선 검사
        for (FoodOrderRequestDto food : foods) {
            Long id = food.getId(); // 음식 id
            int quantity = food.getQuantity(); //음식 수량
            int foodPrice = foodRepository.findByRestaurantIdAndId(restaurantId,id).get().getPrice(); // 레스토랑 id와 음식 id를 통해 음식 가격 받음
            int quantityFoodprice = quantity*foodPrice; // 음식 가격 * 수량을 해서 변수로 만듬
            totalPrice += quantityFoodprice; // 토탈비용에 위에 값을 더함

            if(quantity<1 || quantity>100) {
                throw new IllegalArgumentException("각 음식 주문 수량은 1~100으로 해주세요");
            }
        }

        totalPrice += deliveryFee; //음식 토탈 비용에 배달비용 추가
        System.out.println(totalPrice);
        if(totalPrice - deliveryFee < minOrderPrice){
            throw new IllegalArgumentException("최소배달비용이 안되서 배달 불가입니다.");
        }


        // 음식 id랑 수량 들어있는 객체 배열에서 각 객체를 한개씩 부를 예정
        for (FoodOrderRequestDto food : foods) {
            System.out.println("id : " + food.getId());
            System.out.println("getQuantity : " + food.getQuantity());

            Long id = food.getId(); // 음식 id
            int quantity = food.getQuantity(); //음식 수량

            String foodName = foodRepository.findByRestaurantIdAndId(restaurantId,id).get().getName(); //레스토랑 id와 음식 id를 통해 음식 이름 받음
            System.out.println(foodName);

            int foodPrice = foodRepository.findByRestaurantIdAndId(restaurantId,id).get().getPrice(); // 레스토랑 id와 음식 id를 통해 음식 가격 받음
            System.out.println(foodPrice);

            int quantityFoodprice = quantity*foodPrice; // 음식 가격 * 수량을 해서 변수로 만듬

            FoodOrderDto foodOrderDto = new FoodOrderDto(foodName,quantity,quantityFoodprice); //음식 1종류에 찍힐 내역으로 음식이름,수량,가격을 줌

            orderList.add(foodOrderDto); // 총 영수증으로 각 음식 내역을 더함

            foodOrder order = new foodOrder(restaurantId, id, quantity); //food 오더를 db에 저장하기 위해 준비

            foodorderRepository.save(order); //food 오더 db 저장
        }

        OrderDto orderDto = new OrderDto(restaurantName,orderList,deliveryFee,totalPrice);
//        Order order = new Order(restaurantName,orderList,deliveryFee,totalPrice);
//        orderRepository.save(order);

        return orderDto;
    }

    //주문 조회
    @GetMapping("/orders")
    public List<OrderDto> getOrders() {

        List<foodOrder>ListFood = foodorderRepository.findAllBy(); //food 오더 db에서 주문 내역을 전부 뽑음

        int deliveryFee = 0; //변수들 선언
        String restaurantName ="";
        int totalPrice = 0; //토탈 비용 변수 선언

        List<FoodOrderDto> orderList = new ArrayList<>(); // 음식 name, 수량, price*수량 한 가격이 든 객체 배열을 선언,리스폰할때 필요

        for (foodOrder food : ListFood){

            Long restaurantId=food.getRestaurantId();
            Long foodId=food.getFoodId();

            int foodQuantity=food.getFoodQuantity();
            int foodPrice=foodRepository.getById(foodId).getPrice();
            String foodName = foodRepository.getById(foodId).getName();
            System.out.println(foodName);

            deliveryFee = restaurantRepository.getById(restaurantId).getDeliveryFee();
            restaurantName =restaurantRepository.getById(restaurantId).getName();

            int quantityFoodprice = foodQuantity*foodPrice; // 음식 가격 * 수량을 해서 변수로 만듬

            totalPrice += quantityFoodprice; // 토탈비용에 위에 값을 더함
            FoodOrderDto foodOrderDto = new FoodOrderDto(foodName,foodQuantity,quantityFoodprice); //음식 1종류에 찍힐 내역으로 음식이름,수량,가격을 줌
            orderList.add(foodOrderDto); // 총 영수증으로 각 음식 내역을 더함

        }
        totalPrice += deliveryFee;
        OrderDto orderDto = new OrderDto(restaurantName,orderList,deliveryFee,totalPrice);

        List<OrderDto>result = new ArrayList<>(); //테스트코드에 맞추기 위해 List로 감싸는 중
        result.add(orderDto);
        return result;
    }

}
