package com.example.chat.model;

import java.time.LocalDateTime;

public class ChatMessageVO {
    private Long id;
    private String username;
    private String message;
    private LocalDateTime timestamp;

    // ✅ 생성자
    public ChatMessageVO() {}

    public ChatMessageVO(String username, String message) {
        this.username = username;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Getter & Setter
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setMessage(String message) { this.message = message; }
}
