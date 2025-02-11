package com.example.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DBConnectionTest {

    @Autowired
    private DataSource dataSource; // ✅ DataSource 주입

    @PostConstruct // ✅ 서버 실행 시 자동 실행됨
    public void testConnection() {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("✅ DB 커넥션 성공! 연결된 DB: " + conn.getMetaData().getURL());
            System.out.println("✅ DB 사용자명: " + conn.getMetaData().getUserName());
        } catch (Exception e) {
            System.err.println("❌ DB1 커넥션 실패: " + e.getMessage());
        }
    }
}
