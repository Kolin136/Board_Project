package com.board.board.controller;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;




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

    //슬랙의 다른분은 Delete할시 @RequestHeader를 이용한다 해서 조금 알아보니, 아직 정확한 이유나 원리는 잘 모르지만
    //토큰,세션,jwt등 비밀번호 암호화를 하고 RequestHeader에 담기위해서 사용한다는듯하니
    //지금은 토큰,세션,jwt등 아니라서 RequestBody에 하는게 맞죠? (그래서 비번만 받아도 가능하니 required = false 했습니다)
    @DeleteMapping("/board/{id}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long id,@RequestBody(required = false) BoardRequestDto requestDto){
        Long deleteId = boardService.deleteBoard(id,requestDto);

        if(deleteId!=null){
            return  ResponseEntity.ok().build();
        }
        else{
            System.out.println("비번틀림");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}
