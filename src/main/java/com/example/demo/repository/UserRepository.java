package com.example.demo.repository;

import com.example.demo.domain.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByName(@NotEmpty(message = "사용자 이름은 필수 항목입니다.") String name);
}

