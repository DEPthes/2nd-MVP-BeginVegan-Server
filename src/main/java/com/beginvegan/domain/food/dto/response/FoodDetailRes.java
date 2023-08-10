package com.beginvegan.domain.food.dto.response;

import com.beginvegan.domain.block.dto.BlockDto;
import com.beginvegan.domain.food.dto.FoodIngredientDto;
import com.beginvegan.domain.user.domain.VeganType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class FoodDetailRes {

    private Long id;

    private String name;

    private VeganType veganType;

    private List<FoodIngredientDto> ingredientDtos;

    private List<BlockDto> blockDtos;

    @Builder
    public FoodDetailRes(Long id, String name, VeganType veganType, List<FoodIngredientDto> ingredientDtos, List<BlockDto> blockDtos) {
        this.id = id;
        this.name = name;
        this.veganType = veganType;
        this.ingredientDtos = ingredientDtos;
        this.blockDtos = blockDtos;
    }
}
