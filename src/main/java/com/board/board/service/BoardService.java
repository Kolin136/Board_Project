package com.board.board.service;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.entity.Board;
import com.board.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);

        BoardResponseDto boardResponseDto = new BoardResponseDto(boardRepository.save(board));

        return boardResponseDto;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getIdBoard(Long id) {

        Board board = findBoard(id);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);

        return boardResponseDto;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoards() {

        return boardRepository.findAllByOrderByModifiedAtDesc()
                .stream().map(BoardResponseDto::new).toList();

    }


    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {

        Board board = findBoard(id);
        BoardResponseDto boardResponseDto = null;

        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);
            boardResponseDto = new BoardResponseDto(board);
        } else {
            System.out.println("비번 틀린 메세지");
        }

        return boardResponseDto;
    }

    public Long deleteBoard(Long id, BoardRequestDto requestDto) {

        Board board = findBoard(id);

        if (board.getPassword().equals(requestDto.getPassword())) {
            boardRepository.delete(board);

        } else {
            System.out.println("비번 틀린 메세지");
        }

        return id;
    }


    //JpaRepository의 findById는 Optional리턴이라  null체크를 해줘야한다 그래서 orElseThrow 사용해서
    //찾는값이 있으면 Board반환 하고 없으면 null이 반환되서 예외처리한다.
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 글은 존재하지 않습니다.")
        );
    }




}
