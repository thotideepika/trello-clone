package com.trello.service;

import com.trello.dto.*;
import com.trello.entity.*;
import com.trello.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardListRepository listRepository;

    @Transactional
    public CardDTO createCard(Long listId, String title) {
        BoardList list = listRepository.findById(listId).orElseThrow();
        List<Card> existing = cardRepository.findByListIdOrderByPositionAsc(listId);

        Card card = Card.builder()
                .title(title)
                .description("")
                .position(existing.size())
                .list(list)
                .build();
        card = cardRepository.save(card);
        return toDTO(card);
    }

    @Transactional
    public CardDTO updateCard(Long cardId, CardDTO dto) {
        Card card = cardRepository.findById(cardId).orElseThrow();
        card.setTitle(dto.getTitle());
        card.setDescription(dto.getDescription());
        return toDTO(cardRepository.save(card));
    }

    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }

    @Transactional
    public CardDTO moveCard(MoveCardRequest req) {
        Card card = cardRepository.findById(req.getCardId()).orElseThrow();
        BoardList targetList = listRepository.findById(req.getTargetListId()).orElseThrow();

        card.setList(targetList);
        card.setPosition(req.getNewPosition());
        return toDTO(cardRepository.save(card));
    }

    private CardDTO toDTO(Card card) {
        CardDTO dto = new CardDTO();
        dto.setId(card.getId());
        dto.setTitle(card.getTitle());
        dto.setDescription(card.getDescription());
        dto.setPosition(card.getPosition());
        dto.setListId(card.getList().getId());
        return dto;
    }
}