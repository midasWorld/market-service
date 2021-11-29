package com.midas.marketservice.domain.auth;

import com.midas.marketservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Email
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String encryptedPwd;

    @Builder
    public User(String email, String name, String encryptedPwd) {
        this.email = email;
        this.name = name;
        this.encryptedPwd = encryptedPwd;
    }
}
