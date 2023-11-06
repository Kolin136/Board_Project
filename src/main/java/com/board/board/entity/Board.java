package com.board.board.entity;

import com.board.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "board") //테이블의 이름 설정
@Setter
@Getter
@Entity
@NoArgsConstructor // JPA가 Entity 클래스를 인스턴스화 할 때 기본 생성자를 사용하기 때문에
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk자동생성 하고 자동으로 pk값 1 증가
    private Long id;

    // nullable이 false이면 null불가이고 nullable생략하면 기본 true라 null허용
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "contents", nullable = false, length = 1000)
    private String contents;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();

    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

}
