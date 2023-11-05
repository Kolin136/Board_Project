package com.board.board.dto;

import com.board.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String name;
    private String contents;
    private LocalDateTime createAt;
    private LocalDateTime modiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.name = board.getName();
        this.contents = board.getContents();
        this.createAt = board.getCreateAt();
        this.modiedAt = board.getModifiedAt();

    }
}
