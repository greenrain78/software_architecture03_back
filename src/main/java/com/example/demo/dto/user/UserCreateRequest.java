package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserCreateRequest {
    @Schema(description = "사용자 이름", example = "홍길동")
    @NotEmpty(message = "사용자 이름은 필수 항목입니다.")
    private String name;
}
