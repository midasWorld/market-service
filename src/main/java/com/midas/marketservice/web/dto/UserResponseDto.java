package com.midas.marketservice.web.dto;

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
    public UserResponseDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
