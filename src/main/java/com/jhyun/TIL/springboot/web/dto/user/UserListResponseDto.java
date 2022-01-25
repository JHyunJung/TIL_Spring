package com.jhyun.TIL.springboot.web.dto.user;

import com.jhyun.TIL.springboot.domain.posts.Posts;
import com.jhyun.TIL.springboot.domain.user.User;

import java.time.LocalDateTime;

public class UserListResponseDto {
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    private LocalDateTime modifiedDate;

    public UserListResponseDto(User entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.userPassword = entity.getUserPassword();
        this.userName = entity.getUserName();
        this.modifiedDate = entity.getModifiedDate();
    }
}
