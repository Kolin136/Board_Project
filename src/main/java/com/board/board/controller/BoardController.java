package com.board.board.controller;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto getIdBoard(@PathVariable Long id) {
        return boardService.getIdBoard(id);
    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestHeader("password") String password, @RequestBody(required = false) BoardRequestDto requestDto){
        return boardService.updateBoard(id,password,requestDto);
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id,@RequestHeader("password") String password){
        return boardService.deleteBoard(id,password);
    }


}
