package com.board.board.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class) //이거를 해줘야  자동으로 시간 넣어준다
@MappedSuperclass //Entity 클래스들이  이 클래스를 상속할경우 이 클래스 필드들을 컬럼으로 인식해준다
public abstract class Timestamped {

    @Temporal(TemporalType.TIMESTAMP)  // 자바의 date 객체들 날짜 데이터를 매핑할때 사용 ,
    @CreatedDate // 최초 생성시에만 시간 넣는것
    @Column(updatable = false)  //false하면 변경되지 않도록
    private LocalDateTime createAt;


    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // 조회한 entity객체값을 변경할때 변경된 시간이 modifiendAT변수에 자동으로 저장
    @Column
    private  LocalDateTime modifiedAt;



}
