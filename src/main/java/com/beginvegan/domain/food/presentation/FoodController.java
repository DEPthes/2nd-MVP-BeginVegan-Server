package com.beginvegan.domain.food.presentation;

import com.beginvegan.domain.food.application.FoodService;
import com.beginvegan.domain.food.dto.FoodListRes;
import com.beginvegan.global.payload.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Foods", description = "Foods API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "전체 음식 목록 조회", description = "메인 페이지에서 사용할 정보를 포함한 전체 음식 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "전체 음식 목록 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FoodListRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "전체 음식 목록 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @GetMapping("/food-list")
    public ResponseEntity<?> findAllFoods(){
        return foodService.findAllFoods();
        }

}
