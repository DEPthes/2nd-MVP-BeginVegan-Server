package com.beginvegan.domain.magazine.presentation;

import com.beginvegan.domain.magazine.application.MagazineService;
import com.beginvegan.domain.magazine.dto.request.MagazineDetailReq;
import com.beginvegan.domain.magazine.dto.response.MagazineDetailRes;
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

@Tag(name = "Magazines", description = "Magazines API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/magazines")
public class MagazineController {

    private final MagazineService magazineService;

    // 매거진 상세 정보 조회
    @Operation(summary = "매거진 상세 정보 조회", description = "magazine_id를 통한 매거진 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매거진 상세 정보 조회 성공", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MagazineDetailRes.class)) } ),
            @ApiResponse(responseCode = "400", description = "매거진 상세 정보 조회 실패", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) ) } ),
    })
    @PostMapping("/magazine-detail")
    public ResponseEntity<?> findMagazinedetail(@RequestBody MagazineDetailReq magazineDetailReq) {
        return magazineService.findMagazinedetail(magazineDetailReq);
    }
}
