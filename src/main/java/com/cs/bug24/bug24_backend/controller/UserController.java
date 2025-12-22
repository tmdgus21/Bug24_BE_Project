package com.cs.bug24.bug24_backend.controller;

import com.cs.bug24.bug24_backend.domain.User;
import com.cs.bug24.bug24_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // ← 이 줄 추가

    @PostMapping
    public User create(@RequestBody User user) {
        // 1. 들어온 평문 비밀번호를 암호화
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);

        return userRepository.save(user);
    }

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

}
