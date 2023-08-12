package com.beginvegan.domain.review.presentation;

import com.beginvegan.domain.food.dto.response.FoodRecipeListRes;
import com.beginvegan.domain.review.application.ReviewService;
import com.beginvegan.domain.review.dto.PostReviewReq;
import com.beginvegan.domain.review.dto.ReviewDetailRes;
import com.beginvegan.domain.review.dto.ReviewListRes;
import com.beginvegan.global.config.security.token.CurrentUser;
import com.beginvegan.global.config.security.token.UserPrincipal;
import com.beginvegan.global.payload.ErrorResponse;
import com.beginvegan.global.payload.Message;
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

@Tag(name = "Reviews", description = "Reviews API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "유저의 리뷰 조희", description = "유저의 리뷰들을 최신순으로 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 리뷰 목록 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReviewListRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "유저 리뷰 목록 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @GetMapping
    public ResponseEntity<?> findReviewsByUser(
            @Parameter(description = "Accesstoken을 입력해주세요.", required = true) @CurrentUser UserPrincipal userPrincipal,
            @Parameter(description = "유저의 리뷰 목록을 페이지별로 조회합니다. **Page는 0부터 시작합니다!**", required = true) @RequestParam(value = "page") Integer page
    ) {
        return reviewService.findReviewsByUser(userPrincipal, page);
    }

    @Operation(summary = " 리뷰 등록", description = "리뷰를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Message.class)) } ),
            @ApiResponse(responseCode = "400", description = "리뷰 등록 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @PostMapping
    public ResponseEntity<?> postReview(
            @Parameter(description = "Accesstoken을 입력해주세요.", required = true) @CurrentUser UserPrincipal userPrincipal,
            @Parameter(description = "PostReviewReq Schema를 확인해주세요.", required = true) @RequestBody PostReviewReq postReviewReq
    ) {
        return reviewService.postReview(userPrincipal, postReviewReq);
    }

}
