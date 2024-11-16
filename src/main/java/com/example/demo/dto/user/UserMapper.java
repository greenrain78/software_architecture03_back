package com.example.demo.dto.user;

import com.example.demo.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // 엔티티의 필요한 필드만 매핑하여 변환
    UserResponse toUserDTO(User user);
    User toUserEntity(UserCreateRequest userCreateRequest);
}
