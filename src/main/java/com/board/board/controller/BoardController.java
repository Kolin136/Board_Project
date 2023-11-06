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
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(id,requestDto);
    }

    //슬랙의 다른분은 Delete할시 @RequestHeader를 이용한다 해서 조금 알아보니, 아직 정확한 이유나 원리는 잘 모르지만
    //토큰,세션,jwt등 비밀번호 암호화를 하고 RequestHeader에 담기위해서 사용한다는듯하니
    //지금은 토큰,세션,jwt등 아니라서 RequestBody에 하는게 맞죠?
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id,@RequestBody(required = false) BoardRequestDto requestDto){
        return boardService.deleteBoard(id,requestDto);
    }


}
