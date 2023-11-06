package com.board.board.service;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
    }

    public BoardResponseDto getIdBoard(Long id) {
    }

    public List<BoardResponseDto> getBoards() {
    }


    public BoardResponseDto updateBoard(Long id, String password, BoardRequestDto requestDto) {
    }

    public Long deleteBoard(Long id, String password) {
    }
}
