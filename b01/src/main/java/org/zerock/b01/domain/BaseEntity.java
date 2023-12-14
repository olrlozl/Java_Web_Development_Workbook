package org.zerock.b01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 공통 속성 처리를 위함
@EntityListeners(value = { AuditingEntityListener.class }) // 엔티티가 데이터베이스에 추가되거나 변경될 때 자동으로 시간 값을 지정
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;  // 데이터가 추가된 시간

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;  // 데이터가 수정된 시간

}
