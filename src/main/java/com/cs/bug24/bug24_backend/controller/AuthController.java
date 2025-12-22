package com.cs.bug24.bug24_backend.controller;

import com.cs.bug24.bug24_backend.domain.User;
import com.cs.bug24.bug24_backend.dto.LoginRequest;
import com.cs.bug24.bug24_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        // 1) username으로 유저 찾기
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            // 아이디 없음
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("no such user");
        }

        // 2) 비밀번호 검사 (평문 vs BCrypt 해시)
        boolean matches = passwordEncoder.matches(
                request.getPassword(),      // 사용자가 입력한 평문
                user.getPassword()          // DB에 저장된 암호화 비밀번호
        );

        if (!matches) {
            // 비밀번호 틀림
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("wrong password");
        }

        // 3) 여기까지 통과하면 로그인 성공
        return ResponseEntity.ok("login success");
    }
}
