package com.trello.dto;

import lombok.Data;

@Data
public class MoveCardRequest {
    private Long cardId;
    private Long sourceListId;
    private Long targetListId;
    private Integer newPosition;
}