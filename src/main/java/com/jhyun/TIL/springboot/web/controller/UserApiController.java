package com.jhyun.TIL.springboot.web.controller;

import com.jhyun.TIL.springboot.service.UserService;
import com.jhyun.TIL.springboot.web.dto.user.UserResponseDto;
import com.jhyun.TIL.springboot.web.dto.user.UserSaveRequestDto;
import com.jhyun.TIL.springboot.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @PutMapping("/api/v1/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody
            UserUpdateRequestDto requestDto) {

        return userService.update(id, requestDto);
    }

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto findById (@PathVariable Long id) {

        return userService.findById(id);
    }

    @DeleteMapping("/api/v1/user/{id}")
    public Long delete(@PathVariable Long id) {
        userService.delete(id);
        return id;
    }

    @PostMapping("/api/v1/user/duplicate")
    public boolean checkDuplicate(@PathVariable String userId){
        return userService.checkDuplicate(userId);
    }
}
