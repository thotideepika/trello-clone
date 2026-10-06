package com.trello.dto;

import lombok.Data;

@Data
public class CardDTO {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Long listId;
}