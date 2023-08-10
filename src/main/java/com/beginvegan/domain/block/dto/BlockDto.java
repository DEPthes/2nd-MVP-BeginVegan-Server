package com.beginvegan.domain.block.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class BlockDto {

    private String Content;

    private Integer sequence;

    // content가 이미지일 경우 필요
    private String imageSource;

    @Builder
    public BlockDto(String content, Integer sequence, String imageSource) {
        Content = content;
        this.sequence = sequence;
        this.imageSource = imageSource;
    }
}
