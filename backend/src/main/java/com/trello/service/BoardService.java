package com.trello.service;

import com.trello.dto.*;
import com.trello.entity.*;
import com.trello.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardListRepository listRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public List<BoardDTO> getUserBoards(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return boardRepository.findByOwnerId(user.getId())
                .stream().map(this::toSimpleDTO).collect(Collectors.toList());
    }

    public BoardDTO createBoard(String name, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Board board = Board.builder().name(name).owner(user).build();
        board = boardRepository.save(board);
        return toSimpleDTO(board);
    }

    public BoardDTO getBoard(Long id, String username) {
        Board board = boardRepository.findById(id).orElseThrow();
        if (!board.getOwner().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        return toFullDTO(board);
    }

    public void deleteBoard(Long id, String username) {
        Board board = boardRepository.findById(id).orElseThrow();
        if (!board.getOwner().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        boardRepository.delete(board);
    }

    private BoardDTO toSimpleDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setName(board.getName());
        return dto;
    }

    @Transactional
    public BoardDTO toFullDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setName(board.getName());

        List<BoardList> lists = listRepository.findByBoardIdOrderByPositionAsc(board.getId());
        dto.setLists(lists.stream().map(list -> {
            ListDTO ld = new ListDTO();
            ld.setId(list.getId());
            ld.setName(list.getName());
            ld.setPosition(list.getPosition());

            List<Card> cards = cardRepository.findByListIdOrderByPositionAsc(list.getId());
            ld.setCards(cards.stream().map(c -> {
                CardDTO cd = new CardDTO();
                cd.setId(c.getId());
                cd.setTitle(c.getTitle());
                cd.setDescription(c.getDescription());
                cd.setPosition(c.getPosition());
                cd.setListId(list.getId());
                return cd;
            }).collect(Collectors.toList()));
            return ld;
        }).collect(Collectors.toList()));
        return dto;
    }
}