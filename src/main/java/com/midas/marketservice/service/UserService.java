package com.midas.marketservice.service;

import com.midas.marketservice.domain.auth.User;
import com.midas.marketservice.domain.auth.UserRepository;
import com.midas.marketservice.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long register(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. email=" + email));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. id=" + id));
    }
}
