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
    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json; charset=UTF-8")
    public void sendMessage(@RequestBody ChatMessageVO chatMessage) throws Exception {
        System.out.println("✅ 메시지 수신: " + chatMessage.getUsername() + " - " + chatMessage.getMessage());
        chatMessageDAO.saveMessage(chatMessage);
    }
    // ✅ 최근 메시지 조회 API (GET)
    @GetMapping(value="/messages" ,produces = "application/json; charset=UTF-8")
    public List<ChatMessageVO> getRecentMessages() {
        return chatMessageDAO.getRecentMessages();
    }
}
