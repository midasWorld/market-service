package com.midas.marketservice.service;

import com.midas.marketservice.domain.auth.User;
import com.midas.marketservice.web.dto.UserSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void user_등록한다() throws Exception {
        // given
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email("midas@gmail.com")
                .name("midas")
                .password("1234")
                .build();

        Long savedId = userService.register(requestDto);

        // when
        User findUser = userService.findById(savedId);

        // then
        assertThat(savedId).isEqualTo(findUser.getId());
    }
}