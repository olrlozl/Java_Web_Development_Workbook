package org.zerock.b02.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b02.domain.Board;
import org.zerock.b02.dto.BoardListReplyCountDTO;

public interface BoardSearch { // Querydsl을 이용할 인터페이스

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
