package com.trello.service;

import com.trello.dto.ListDTO;
import com.trello.entity.*;
import com.trello.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {

    private final BoardListRepository listRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ListDTO createList(Long boardId, String name) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        List<BoardList> existing = listRepository.findByBoardIdOrderByPositionAsc(boardId);

        BoardList list = BoardList.builder()
                .name(name)
                .position(existing.size())
                .board(board)
                .build();
        list = listRepository.save(list);

        ListDTO dto = new ListDTO();
        dto.setId(list.getId());
        dto.setName(list.getName());
        dto.setPosition(list.getPosition());
        return dto;
    }

    public void deleteList(Long listId) {
        listRepository.deleteById(listId);
    }
}