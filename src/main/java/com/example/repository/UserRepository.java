package com.example.repository;

import com.example.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    // 간단히 하드코딩된 데이터를 반환
    public User findUserById(int id) {
        return new User(id, "John Doe");
    }
}
