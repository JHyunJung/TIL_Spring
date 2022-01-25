package com.jhyun.TIL.springboot.web.api;

import com.jhyun.TIL.springboot.domain.posts.Posts;
import com.jhyun.TIL.springboot.domain.user.User;
import com.jhyun.TIL.springboot.domain.user.UserRepository;
import com.jhyun.TIL.springboot.web.dto.user.UserSaveRequestDto;
import com.jhyun.TIL.springboot.web.dto.user.UserUpdateRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception{
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception {
        //given

        String userId = "jhyun";
        String userPassword = "1234567890!";
        String userName = "재현";

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getUserName()).isEqualTo(userName);
    }

    @Test
    public void 회원정보수정() throws Exception{
        //given

        String userId = "jhyun";
        String userPassword = "1234567890";
        String userName = "재현";

        User saveUser = userRepository.save(User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build());

        Long updatedId = saveUser.getId();
        String expecteduserId = "jhyun";
        String expecteduserPassword = "0987654321";
        String expecteduserName = "수영";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .userPassword(expecteduserPassword)
                .userName(expecteduserName)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user/" + updatedId;
        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getUserPassword()).isEqualTo(expecteduserPassword);
        assertThat(all.get(0).getUserName()).isEqualTo(expecteduserName);
    }

    @Test
    public void 중복회원가입() throws Exception{
        //given

        String userId = "jhyun";
        String userPassword = "1234567890";
        String userName = "재현";

        User saveUser = userRepository.save(User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build());

        Long updatedId = saveUser.getId();
        String expecteduserId = "jhyun";
        String expecteduserPassword = "0987654321";
        String expecteduserName = "수영";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .userPassword(expecteduserPassword)
                .userName(expecteduserName)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user/duplicate?userId=" + expecteduserId;
        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
