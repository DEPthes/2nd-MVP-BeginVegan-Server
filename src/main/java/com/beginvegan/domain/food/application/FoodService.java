package com.beginvegan.domain.food.application;

import com.beginvegan.domain.food.domain.Food;
import com.beginvegan.domain.food.domain.repository.FoodRepository;
import com.beginvegan.domain.food.dto.FoodIngredientDto;
import com.beginvegan.domain.food.dto.FoodRecipeListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    // 레시피 전체 조회 : 재료 포함 :: 하단 바 레시피 클릭 시 화면
    @Transactional
    public ResponseEntity<?> findAllFoodsWithIngredients() {
        List<Food> foods = foodRepository.findAll();
        List<FoodRecipeListRes> foodDtos = new ArrayList<>();

        for (Food food : foods) {
            List<FoodIngredientDto> foodIngredientDtos = food.getIngredients().stream()
                    .map(ingredient -> FoodIngredientDto.builder()
                            .id(ingredient.getId())
                            .name(ingredient.getName())
                            .build())
                    .collect(Collectors.toList());

            FoodRecipeListRes foodRecipeListRes = FoodRecipeListRes.builder()
                    .id(food.getId())
                    .name(food.getName())
                    .veganType(food.getVeganType())
                    .ingredients(foodIngredientDtos)
                    .build();

            foodDtos.add(foodRecipeListRes);
        }

        return ResponseEntity.ok(foodDtos);
    }

}