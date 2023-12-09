package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page; // 현재 페이지 번호
    private int size; // 한 페이지당 보여주는 데이터의 수
    private int total; // 전체 데이터의 개수

    private int start; // 시작 페이지 번호
    private int end; // 끝 페이지 번호

    private boolean prev; // 이전 페이지의 존재 여부
    private boolean next; // 다음 페이지의 존재 여부

    private List<E> dtoList; // TodoDTO 목록

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil((total/(double)size)));
        this.end = end > last ? last : end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}
