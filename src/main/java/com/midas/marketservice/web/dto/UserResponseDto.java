package com.midas.marketservice.web.dto;

import com.midas.marketservice.domain.auth.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String name;
    private String password;

    @Builder
    public UserResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getEncryptedPwd();
    }
}
