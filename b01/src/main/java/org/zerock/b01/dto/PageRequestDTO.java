package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1; // 현재 페이지 번호

    @Builder.Default
    private int size = 10; // 한 페이지당 보여주는 데이터의 수

    private String type; // 검색의 종류 t, c, w, tc, tw, twc

    private String keyword; // 키워드

    private String link; // 페이지 이동에 필요한 링크


    public String[] getTypes() { // 현재 검색 조건들을 BoardRepository에서 String[]로 처리하기 때문에
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type.split(""); // type이라는 문자열을 배열로 반환
    }

    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink() {
        if (link == null) {
            StringBuilder builder = new StringBuilder();

            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (type != null && type.length() > 0) {
                builder.append("&type=" + type);
            }

            if (keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
            link = builder.toString();
        }
        return link;
    }
}
