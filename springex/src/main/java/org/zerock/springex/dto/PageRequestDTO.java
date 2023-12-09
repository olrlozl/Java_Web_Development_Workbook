package org.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1; // 현재 페이지 번호

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10; // 한 페이지당 보여주는 데이터의 수

    private String link; // 페이지 이동에 필요한 링크

    private String[] types; // 검색 종류

    private String keyword; // 검색 키워드

    private boolean finished; // 완료 여부

    private LocalDate from; // 필터링 기간 시작

    private LocalDate to; // 필터링 기간 끝

    public int getSkip() { // limit에서 사용하는 건너뛰기의 수

        return (page - 1) * size;
    }

    public String getLink() {

        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        if (finished) {
            builder.append("&finished=on");
        }

        if (types != null && types.length > 0) {
            for (int i = 0; i < types.length; i++) {
                builder.append("&types=" + types[i]);
            }
        }

        if (keyword != null) {
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (from != null) {
            builder.append("&from=" + from.toString());
        }

        if (to != null) {
            builder.append("&to=" + to.toString());
        }

        return builder.toString();
    }

    public boolean checkType(String type) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>" + type);

        if (types == null || types.length == 0) {
            return false;

        }

        System.out.println("<<<<<<<<<<<<<<<<<<" + Arrays.stream(types).anyMatch(type::equals));
        return Arrays.stream(types).anyMatch(type::equals);
    }

}
