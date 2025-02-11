package com.example.chat.config;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
	
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;
    
    
    @Bean
    public DataSource dataSource() {
        System.out.println("✅ DB 연결 URL: " + dbUrl);  // 로그 출력
        System.out.println("✅ DB 사용자명: " + dbUsername);
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        //커넥션 풀 설정
        dataSource.setMaxTotal(10);  // ✅ 최대 커넥션 개수
        dataSource.setMaxIdle(5);    // ✅ 유휴 커넥션 개수
        dataSource.setMinIdle(2);
        
        return dataSource;
    }

}
