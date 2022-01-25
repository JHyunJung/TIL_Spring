package com.jhyun.TIL.springboot.service;


import com.jhyun.TIL.springboot.domain.user.User;
import com.jhyun.TIL.springboot.domain.user.UserRepository;
import com.jhyun.TIL.springboot.web.dto.user.UserResponseDto;
import com.jhyun.TIL.springboot.web.dto.user.UserSaveRequestDto;
import com.jhyun.TIL.springboot.web.dto.user.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {

        if(userRepository.existsByUserId(requestDto.getUserId())){
            throw new RuntimeException("이미 존재하는 유저 입니다.");
        }

        String encodedPassword = passwordEncoder.encode(requestDto.getUserPassword());

        User userSaveRequestDto = User.builder()
                .userId(requestDto.getUserId())
                .userPassword(encodedPassword)
                .userName(requestDto.getUserName())
                .build();


        return userRepository.save(userSaveRequestDto).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 없습니다. id=" + id));

        user.update(requestDto.getUserPassword(), requestDto.getUserName());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 없습니다 id=" + id));

        userRepository.delete(user);
    }

    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 없습니다 id=" + id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public boolean checkDuplicate(String userId){
        return userRepository.existsByUserId(userId);
    }




}
