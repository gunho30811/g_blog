package com.example.chat.model;

import com.example.chat.model.ChatMessageVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChatMessageDAO {

    private final JdbcTemplate jdbcTemplate;

    public ChatMessageDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ✅ 메시지 저장 (INSERT)
    public void saveMessage(ChatMessageVO message) {
        String sql = "INSERT INTO chat_Messages (username, message, timestamp) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, message.getUsername(), message.getMessage());
    }

    // ✅ 최근 메시지 가져오기 (SELECT)
    public List<ChatMessageVO> getRecentMessages() {
        String sql = "SELECT id, username, message, timestamp FROM chat_Messages ORDER BY timestamp DESC LIMIT 50";
        return jdbcTemplate.query(sql, new ChatMessageRowMapper());
    }

    // ✅ RowMapper (DB 결과를 객체로 변환)
    private static class ChatMessageRowMapper implements RowMapper<ChatMessageVO> {
        @Override
        public ChatMessageVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChatMessageVO message = new ChatMessageVO();
            message.setId(rs.getLong("id"));
            message.setUsername(rs.getString("username"));
            message.setMessage(rs.getString("message"));
            message.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            return message;
        }
    }
}
