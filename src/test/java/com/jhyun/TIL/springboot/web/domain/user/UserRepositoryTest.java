package com.jhyun.TIL.springboot.web.domain.user;

import com.jhyun.TIL.springboot.domain.user.User;
import com.jhyun.TIL.springboot.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanRepository() {
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입(){
        //givenㄲ
        String userId = "test";
        String userPassword = "0123456789";
        String userName = "재현";

        userRepository.save(User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build());
        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);

        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    public void 중복회원가입체크(){
        //given
        String userId = "test";
        String userPassword = "0123456789";
        String userName = "재현";

        userRepository.save(User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build());
        //when
        String userId2 = "test";
        String userPassword2 = "1234";
        String userName2 = "민석";

        //then
        boolean result = userRepository.existsByUserId(userId2);
        assertThat(result).isTrue();
    }
}