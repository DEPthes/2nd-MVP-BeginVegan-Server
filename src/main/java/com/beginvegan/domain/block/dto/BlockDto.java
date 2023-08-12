package com.beginvegan.domain.block.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BlockDto {

    private Long id;

    private String content;

    private Integer sequence;

    private String source;

    @Builder
    public BlockDto(Long id, String content, Integer sequence, String source) {
        this.id = id;
        this.content = content;
        this.sequence = sequence;
        this.source = source;
    }
}
