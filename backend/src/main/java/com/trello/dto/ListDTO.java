package com.trello.dto;

import lombok.Data;
import java.util.List;

@Data
public class ListDTO {
    private Long id;
    private String name;
    private Integer position;
    private List<CardDTO> cards;
}