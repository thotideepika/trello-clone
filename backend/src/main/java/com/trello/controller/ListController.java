package com.trello.controller;

import com.trello.dto.ListDTO;
import com.trello.service.ListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lists")
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    @PostMapping
    public ListDTO createList(@RequestBody Map<String, Object> body) {
        Long boardId = Long.valueOf(body.get("boardId").toString());
        String name = body.get("name").toString();
        return listService.createList(boardId, name);
    }

    @DeleteMapping("/{id}")
    public void deleteList(@PathVariable Long id) {
        listService.deleteList(id);
    }
}