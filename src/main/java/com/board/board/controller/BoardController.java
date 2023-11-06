package com.board.board.controller;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


//시간이 부족해서 코드 중복처리(서비스쪽의 패스워드 체크등),삭제 메소드 상태처리는 못 했습니다.

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

    //ResponseEntity는 상태코드도 같이 보내고 싶을때
    @GetMapping("/board/{id}")
    public ResponseEntity<Object> getIdBoard(@PathVariable Long id) {

        try
        {
            //body까지 같이 리턴 하고 싶으면 ok안에 넣기
            return  ResponseEntity.ok(boardService.getIdBoard(id));
        }
        catch (Exception e)
        {
            //NOT_FOUND는 404에러
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/board")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }


    @PutMapping("/board/{id}")
    public  ResponseEntity<Object> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        BoardResponseDto boardResponseDto = boardService.updateBoard(id,requestDto);

          if(boardResponseDto!=null){
              return  ResponseEntity.ok(boardResponseDto);
          }
          else{
              System.out.println("비번틀림");
              return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
          }

    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable Long id,@RequestBody(required = false) BoardRequestDto requestDto){
        return boardService.deleteBoard(id,requestDto);
    }


}
