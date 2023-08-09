package com.beginvegan.domain.magazine.presentation;

import com.beginvegan.domain.magazine.application.MagazineService;
import com.beginvegan.domain.magazine.dto.response.MagazineListRes;
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

@Tag(name = "Magazines", description = "Magazines API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/magazines")
public class MagazineController {

    private final MagazineService magazineService;

    // 랜덤 매거진 2가지 조회
    @Operation(summary = "2가지 매거진 목록 조회", description = "2가지 매거진 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "2가지 매거진 목록 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MagazineListRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "2가지 매거진 목록 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @GetMapping("/random-megazine-list")
    public ResponseEntity<?> findTwoMagazines(){
        return magazineService.findTwoMagazines();
    }
}
