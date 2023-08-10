package com.beginvegan.domain.magazine.dto.response;

import com.beginvegan.domain.block.dto.BlockDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class MagazineDetailRes {

    private Long id;

    private String title;

    private String editor;

    private List<BlockDto> magazineBlocks;

    @Builder
    public MagazineDetailRes(Long id, String title, String editor, List<BlockDto> magazineBlocks) {
        this.id = id;
        this.title = title;
        this.editor = editor;
        this.magazineBlocks = magazineBlocks;
    }
}
