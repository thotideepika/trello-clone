package com.trello.controller;

import com.trello.dto.CardDTO;
import com.trello.dto.MoveCardRequest;
import com.trello.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public CardDTO createCard(@RequestBody Map<String, Object> body) {
        Long listId = Long.valueOf(body.get("listId").toString());
        String title = body.get("title").toString();
        CardDTO card = cardService.createCard(listId, title);
        messagingTemplate.convertAndSend("/topic/board/" + body.get("boardId"), Map.of("type", "CARD_CREATED", "payload", card));
        return card;
    }

    @PutMapping("/{id}")
    public CardDTO updateCard(@PathVariable Long id, @RequestBody CardDTO dto) {
        return cardService.updateCard(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    @PostMapping("/move")
    public CardDTO moveCard(@RequestBody MoveCardRequest req) {
        CardDTO card = cardService.moveCard(req);
        messagingTemplate.convertAndSend("/topic/board/updates", Map.of("type", "CARD_MOVED", "payload", card));
        return card;
    }
}