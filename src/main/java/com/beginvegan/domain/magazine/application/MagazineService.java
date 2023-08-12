package com.beginvegan.domain.magazine.application;

import com.beginvegan.domain.magazine.domain.Magazine;
import com.beginvegan.domain.magazine.domain.MagazineType;
import com.beginvegan.domain.magazine.domain.repository.MagazineRepository;
import com.beginvegan.domain.magazine.dto.response.MagazineListRes;
import com.beginvegan.global.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MagazineService {

    private final MagazineRepository magazineRepository;

    // 2가지 매거진 랜덤 조회 : 메인 페이지
    public ResponseEntity<?> findTwoMagazines() {
        List<Magazine> magazines = magazineRepository.findAll();
        List<Magazine> magazines_meaning = magazineRepository.findAllByMagazineType(MagazineType.VEGAN_MEANING);
        List<Magazine> magazines_type = magazineRepository.findAllByMagazineType(MagazineType.VEGAN_TYPE);

        // 랜덤 수 1개씩 추리기 :: 비건 정의 1, 비건 종류 1
        int randomNum_meaning = (int)(Math.random() * magazines_meaning.size());
        int randomNum_type = (int)(Math.random() * magazines_type.size());

        Magazine magazine_meaning = magazines_meaning.get(randomNum_meaning);
        Magazine magazine_type = magazines_type.get(randomNum_type);

        List<MagazineListRes> magazineList = new ArrayList<>();

        MagazineListRes magazineListRes = MagazineListRes.builder()
                .id(magazine_meaning.getId())
                .title(magazine_meaning.getTitle())
                .editor(magazine_meaning.getEditor())
                .magazineType(magazine_meaning.getMagazineType())
                .build();
        magazineList.add(magazineListRes);

        magazineListRes = MagazineListRes.builder()
                .id(magazine_type.getId())
                .title(magazine_type.getTitle())
                .editor(magazine_type.getEditor())
                .magazineType(magazine_type.getMagazineType())
                .build();
        magazineList.add(magazineListRes);

        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(magazineList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
