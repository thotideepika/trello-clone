package com.trello.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class WebSocketController {

    @MessageMapping("/board/{boardId}")
    @SendTo("/topic/board/{boardId}")
    public Map<String, Object> broadcast(@DestinationVariable Long boardId, Map<String, Object> message) {
        return message;
    }
}