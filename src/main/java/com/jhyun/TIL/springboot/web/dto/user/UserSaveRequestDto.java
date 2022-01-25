package com.jhyun.TIL.springboot.web.dto.user;

import com.jhyun.TIL.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String userId;
    private String userPassword;
    private String userName;

    @Builder
    public UserSaveRequestDto(String userId, String userPassword, String userName){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .build();
    }
}
