package com.beginvegan.domain.food.presentation;

import com.beginvegan.domain.food.application.FoodService;
import com.beginvegan.domain.food.dto.request.FoodDetailReq;
import com.beginvegan.domain.food.dto.response.FoodDetailRes;
import com.beginvegan.global.payload.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Foods", description = "Foods API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    // 레시피 상세 정보 조회
    @Operation(summary = "레시피 상세 정보 조회", description = "food_id를 통한 레시피 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "레시피 상세 정보 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FoodDetailRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "레시피 상세 정보 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @PostMapping("/recipe-detail")
    public ResponseEntity<?> findFoodDetail(@RequestBody FoodDetailReq foodDetailReq) { // @RequestParam Long id
        return foodService.findFoodDetail(foodDetailReq);
    }
}
