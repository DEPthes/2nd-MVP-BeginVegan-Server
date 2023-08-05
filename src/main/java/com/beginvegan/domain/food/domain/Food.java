package com.beginvegan.domain.food.domain;

import com.beginvegan.domain.common.BaseEntity;
import com.beginvegan.domain.block.domain.Block;
import com.beginvegan.domain.user.domain.VeganType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Food extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private VeganType veganType;

    @OneToMany(fetch = FetchType.LAZY)
    private List<FoodIngredient> ingredients;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Block> foodBlocks;

    @Builder
    public Food(Long id, String name, VeganType veganType, List<FoodIngredient> ingredients, List<Block> foodBlocks) {
        this.id = id;
        this.name = name;
        this.veganType = veganType;
        this.ingredients = ingredients;
        this.foodBlocks = foodBlocks;
    }

}
