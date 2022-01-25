package com.jhyun.TIL.springboot.web.dto.user;

import com.jhyun.TIL.springboot.domain.posts.Posts;
import com.jhyun.TIL.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserResponseDto {
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.userPassword = entity.getUserPassword();
        this.userName = entity.getUserName();
    }
}
