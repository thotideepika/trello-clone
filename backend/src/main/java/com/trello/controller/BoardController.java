package com.trello.controller;

import com.trello.dto.BoardDTO;
import com.trello.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardDTO> getBoards(Authentication auth) {
        return boardService.getUserBoards(auth.getName());
    }

    @PostMapping
    public BoardDTO createBoard(@RequestBody Map<String, String> body, Authentication auth) {
        return boardService.createBoard(body.get("name"), auth.getName());
    }

    @GetMapping("/{id}")
    public BoardDTO getBoard(@PathVariable Long id, Authentication auth) {
        return boardService.getBoard(id, auth.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id, Authentication auth) {
        boardService.deleteBoard(id, auth.getName());
    }
}