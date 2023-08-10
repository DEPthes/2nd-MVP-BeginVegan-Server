package com.beginvegan.domain.magazine.application;

import com.beginvegan.domain.block.dto.BlockDto;
import com.beginvegan.domain.magazine.domain.Magazine;
import com.beginvegan.domain.magazine.domain.repository.MagazineRepository;
import com.beginvegan.domain.magazine.dto.request.MagazineDetailReq;
import com.beginvegan.domain.magazine.dto.response.MagazineDetailRes;
import com.beginvegan.domain.magazine.exception.MagazineNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MagazineService {

    private final MagazineRepository magazineRepository;

    // 매거진 상세 조회 : id를 통해 조회
    @Transactional
    public ResponseEntity<?> findMagazinedetail(MagazineDetailReq magazineDetailReq) {
        Optional<Magazine> magazineOptional = magazineRepository.findById(magazineDetailReq.getId());
        Magazine magazine = magazineOptional.orElseThrow(() -> new MagazineNotFoundException("해당 아이디를 가진 매거진을 찾을 수 없습니다. ID: " + magazineDetailReq.getId()));

        List<BlockDto> blockDtos = magazine.getMagazineBlocks().stream()
                .map(block -> BlockDto.builder()
                        .content(block.getContent())
                        .sequence(block.getSequence())
                        .imageSource(block.getImageSource())
                        .build())
                .collect(Collectors.toList());

        MagazineDetailRes magazineDetailRes = MagazineDetailRes.builder()
                .id(magazine.getId())
                .title(magazine.getTitle())
                .editor(magazine.getEditor())
                .magazineBlocks(blockDtos)
                .build();

        return ResponseEntity.ok(magazineDetailRes);
    }
}
