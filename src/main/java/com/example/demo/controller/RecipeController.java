package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RecipeController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("정상 동작 테스트");
        return ResponseEntity.ok("정상 동작 테스트");
    }
}
