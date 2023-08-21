package com.beginvegan.domain.magazine.application;

import com.beginvegan.domain.block.dto.BlockDto;
import com.beginvegan.domain.magazine.domain.Magazine;
import com.beginvegan.domain.magazine.domain.MagazineType;
import com.beginvegan.domain.magazine.domain.repository.MagazineRepository;
import com.beginvegan.domain.magazine.dto.request.MagazineDetailReq;
import com.beginvegan.domain.magazine.dto.response.MagazineDetailRes;
import com.beginvegan.domain.magazine.dto.response.MagazineListRes;
import com.beginvegan.domain.magazine.exception.MagazineNotFoundException;
import com.beginvegan.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MagazineService {

    private final MagazineRepository magazineRepository;

    // 2가지 매거진 조회 : 메인 페이지
    public ResponseEntity<?> findTwoMagazines() {
        List<Magazine> magazines = magazineRepository.findAll();

        List<MagazineListRes> magazineList = new ArrayList<>();

        for (Magazine magazine : magazines) {
            MagazineListRes magazineListRes = MagazineListRes.builder()
                    .id(magazine.getId())
                    .title(magazine.getTitle())
                    .editor(magazine.getEditor())
                    .magazineType(magazine.getMagazineType())
                    .build();
            magazineList.add(magazineListRes);
        }

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(magazineList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    // 매거진 상세 조회 : id를 통해 조회
    public ResponseEntity<?> findMagazineDetail(MagazineDetailReq magazineDetailReq) {
        Optional<Magazine> magazineOptional = magazineRepository.findMagazineById(magazineDetailReq.getId());
        Magazine magazine = magazineOptional.orElseThrow(() -> new MagazineNotFoundException("해당 아이디를 가진 매거진을 찾을 수 없습니다. ID: " + magazineDetailReq.getId()));

        List<BlockDto> blockDtos = magazine.getMagazineBlocks().stream()
                .map(block -> BlockDto.builder()
                        .content(block.getContent())
                        .sequence(block.getSequence())
                        .build())
                .sorted(Comparator.comparing(BlockDto::getSequence))
                .collect(Collectors.toList());

        MagazineDetailRes magazineDetailRes = MagazineDetailRes.builder()
                .id(magazine.getId())
                .title(magazine.getTitle())
                .editor(magazine.getEditor())
                .source(magazine.getSource())
                .magazineType(magazine.getMagazineType())
                .magazineContents(blockDtos) // magazineBlocks
                .build();

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(magazineDetailRes)
                .build();

        return ResponseEntity.ok(apiResponse);

    }
}
