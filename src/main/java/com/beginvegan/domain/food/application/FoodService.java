package com.beginvegan.domain.food.application;

import com.beginvegan.domain.block.dto.BlockDto;
import com.beginvegan.domain.food.domain.Food;
import com.beginvegan.domain.food.domain.repository.FoodRepository;
import com.beginvegan.domain.food.dto.FoodIngredientDto;
import com.beginvegan.domain.food.dto.request.FoodDetailReq;
import com.beginvegan.domain.food.dto.response.FoodDetailRes;
import com.beginvegan.domain.food.exception.FoodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    // food_id를 통한 레시피 검색
    @Transactional
    public ResponseEntity<?> findFoodDetail(FoodDetailReq foodDetailReq) {
        Optional<Food> foodOptional = foodRepository.findById(foodDetailReq.getId());
        Food food = foodOptional.orElseThrow(() -> new FoodNotFoundException("해당 아이디를 가진 음식을 찾을 수 없습니다. ID: " + foodDetailReq.getId()));

        List<FoodIngredientDto> ingredientDtos = food.getIngredients().stream()
                .map(ingredient -> FoodIngredientDto.builder()
                        .id(ingredient.getId())
                        .name(ingredient.getName())
                        .build())
                .collect(Collectors.toList());

        List<BlockDto> blockDtos = food.getFoodBlocks().stream()
                .map(block -> BlockDto.builder()
                        .id(block.getId())
                        .content(block.getContent())
                        .sequence(block.getSequence())
                        .imageSource(block.getImageSource())
                        .build())
                .collect(Collectors.toList());

        FoodDetailRes foodDetailRes = FoodDetailRes.builder()
                .id(food.getId())
                .name(food.getName())
                .veganType(food.getVeganType())
                .ingredientDtos(ingredientDtos)
                .blockDtos(blockDtos)
                .build();

        return ResponseEntity.ok(foodDetailRes);

    }
}
