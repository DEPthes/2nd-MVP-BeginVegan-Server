package com.beginvegan.domain.magazine.application;

import com.beginvegan.domain.magazine.domain.Magazine;
import com.beginvegan.domain.magazine.domain.repository.MagazineRepository;
import com.beginvegan.domain.magazine.dto.response.MagazineListRes;
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
        List<MagazineListRes> magazineList = new ArrayList<MagazineListRes>();

        // 랜덤 수 2개 추리기
        Set<Integer> randomNum = new HashSet<>();
        while(randomNum.size() < 2){
            randomNum.add((int)(Math.random() * magazines.size()));
        }

        Iterator<Integer> iter = randomNum.iterator();
        while(iter.hasNext()){
            int num = iter.next();
            MagazineListRes magazineListRes = MagazineListRes.builder()
                    .id(magazines.get(num).getId())
                    .title(magazines.get(num).getTitle())
                    .editor(magazines.get(num).getEditor())
                    .build();
            magazineList.add(magazineListRes);
        }

        return ResponseEntity.ok(magazineList);
    }
}
