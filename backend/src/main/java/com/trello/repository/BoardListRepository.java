package com.trello.repository;

import com.trello.entity.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {
    List<BoardList> findByBoardIdOrderByPositionAsc(Long boardId);
}