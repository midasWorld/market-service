package com.midas.marketservice.web;

import com.midas.marketservice.domain.auth.User;
import com.midas.marketservice.service.UserService;
import com.midas.marketservice.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
