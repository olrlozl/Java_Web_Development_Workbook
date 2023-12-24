package org.zerock.b02.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b02.domain.Board;
import org.zerock.b02.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch { // BoardSearch: 부모 인터페이스로 Querydsl을 위한 인터페이스 지정

    // 쿼리 메소드
    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

    // @Query (JPQL)
    @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
    Page<Board> findKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()", nativeQuery = true) // 특정 데이터베이스에서 동작한는 SQL 사용
    String getTime();
}
