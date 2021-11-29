package com.midas.marketservice.web;

import com.midas.marketservice.domain.auth.User;
import com.midas.marketservice.domain.auth.UserRepository;
import com.midas.marketservice.web.dto.UserSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @Autowired private UserRepository userRepository;

    @Test
    public void user_등록된다() throws Exception {
        // given
        String email = "midas@gmail.com";
        String name = "midas";

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email)
                .name(name)
                .password("1234")
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(name);
    }
}