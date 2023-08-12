package com.beginvegan.domain.restaurant.presentation;

import com.beginvegan.domain.restaurant.application.RestaurantService;
import com.beginvegan.domain.restaurant.dto.RestaurantAndMenusRes;
import com.beginvegan.domain.restaurant.dto.RestaurantDetailRes;
import com.beginvegan.domain.review.dto.ReviewListRes;
import com.beginvegan.global.config.security.token.CurrentUser;
import com.beginvegan.global.config.security.token.UserPrincipal;
import com.beginvegan.global.payload.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurants", description = "Restaurants API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "식당/카페 상세 정보(메뉴까지) 조희", description = "식당/카페 상세 정보(메뉴까지)를 조희합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "식당/카페 상세 정보(메뉴까지) 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantAndMenusRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "식당/카페 상세 정보(메뉴까지) 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @GetMapping("/{restaurant-id}")
    public ResponseEntity<?> findRestaurantById(
            @Parameter(description = "식당/카페를 ID로 조회합니다.", required = true) @PathVariable(value = "restaurant-id") Long restaurantId
    ) {
        return restaurantService.findRestaurantById(restaurantId);
    }

}
