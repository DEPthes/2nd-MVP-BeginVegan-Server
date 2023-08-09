package com.beginvegan.domain.food.application;

import com.beginvegan.domain.food.domain.Food;
import com.beginvegan.domain.food.domain.repository.FoodRepository;
import com.beginvegan.domain.food.dto.FoodListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public ResponseEntity<?> findAllFoods() {
        List<Food> foods = foodRepository.findAll();
        List<FoodListRes> foodList = new ArrayList<>();

        for (Food food : foods) {
            FoodListRes foodListRes = FoodListRes.builder()
                    .id(food.getId())
                    .name(food.getName())
                    .veganType(food.getVeganType())
                    .description(food.getDescription())
                    .build();
            foodList.add(foodListRes);
        }

        return ResponseEntity.ok(foodList);
    }

}
