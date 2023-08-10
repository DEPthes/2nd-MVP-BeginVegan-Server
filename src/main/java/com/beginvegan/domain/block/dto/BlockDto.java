package com.beginvegan.domain.block.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BlockDto {

    private Long id;

    private String content;

    private Integer sequence;

    // content가 이미지일 시 필요
    private String imageSource;

    @Builder
    public BlockDto(Long id, String content, Integer sequence, String imageSource) {
        this.id = id;
        this.content = content;
        this.sequence = sequence;
        this.imageSource = imageSource;
    }
}
