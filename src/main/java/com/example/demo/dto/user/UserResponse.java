package com.example.demo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
    @Schema(description = "사용자 아이디", example = "1")
    private Long id;
}
