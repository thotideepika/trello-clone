package com.trello.dto;

import lombok.Data;
import java.util.List;

@Data
public class BoardDTO {
    private Long id;
    private String name;
    private List<ListDTO> lists;
}