package com.beginvegan.domain.block.dto;

import com.beginvegan.domain.block.domain.BlockType;
import lombok.Builder;
import lombok.Data;

@Data
public class BlockDto {

    private String content;

    private Integer sequence;

    private BlockType blockType;

    @Builder
    public BlockDto(Long id, String content, Integer sequence, BlockType blockType) {
        this.content = content;
        this.sequence = sequence;
        this.blockType = blockType;
    }
}
