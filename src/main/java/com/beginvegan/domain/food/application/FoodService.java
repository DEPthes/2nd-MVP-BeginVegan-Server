package com.beginvegan.domain.food.application;

import com.beginvegan.domain.food.domain.Food;
import com.beginvegan.domain.food.domain.repository.FoodRepository;
import com.beginvegan.domain.food.dto.response.FoodListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    // 3가지 음식 랜덤 조회 : 메인 페이지
    public ResponseEntity<?> findThreeFoods() {
        List<Food> foods = foodRepository.findAll();
        List<FoodListRes> foodList = new ArrayList<>();

        // 랜덤 수 3개 추리기
        Set<Integer> randomNum = new HashSet<>();
        while(randomNum.size() < 3){
            randomNum.add((int)(Math.random() * foods.size()));
        }

        Iterator<Integer> iter = randomNum.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            FoodListRes foodListRes = FoodListRes.builder()
                    .id(foods.get(num).getId())
                    .name(foods.get(num).getName())
                    .veganType(foods.get(num).getVeganType())
                    .description(foods.get(num).getDescription())
                    .build();
            foodList.add(foodListRes);
        }

        return ResponseEntity.ok(foodList);
    }

}