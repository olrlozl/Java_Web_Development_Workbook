package org.zerock.b02.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board") // 참조하는 객체를 사용하지 않도록 반드시 exclude 속성값을 지정.
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스에 위임 - auto_increment
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY) // 연관 관계를 나타낼 때는 반드시 fetch 속성은 LAZY로 지정. 지연로딩: 필요한 순간까지 db와 연결하지 않는 방식
    private Board board;

    private String replyText;

    private String replyer;

    public void changeText(String text) {
        this.replyText = text;
    }
}
