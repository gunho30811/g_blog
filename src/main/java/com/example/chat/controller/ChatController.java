package com.example.chat.controller;

import com.example.chat.model.ChatMessageVO;
import com.example.chat.model.ChatMessageDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*") // ✅ Vue와 CORS 문제 방지
public class ChatController {

    private final ChatMessageDAO chatMessageDAO;

    public ChatController(ChatMessageDAO chatMessageDAO) {
        this.chatMessageDAO = chatMessageDAO;
    }

    // ✅ 메시지 저장 API (POST)
    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatMessageVO chatMessage) {
        chatMessageDAO.saveMessage(chatMessage);
    }
    // ✅ 최근 메시지 조회 API (GET)
    @GetMapping("/messages")
    public List<ChatMessageVO> getRecentMessages() {
        return chatMessageDAO.getRecentMessages();
    }
}
