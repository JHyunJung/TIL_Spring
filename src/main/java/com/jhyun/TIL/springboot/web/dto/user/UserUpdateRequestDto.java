package com.jhyun.TIL.springboot.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String userId;
    private String userPassword;
    private String userName;

    @Builder
    public UserUpdateRequestDto(String userPassword, String userName){
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
