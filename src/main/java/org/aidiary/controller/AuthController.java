package org.aidiary.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aidiary.dto.LoginDTO;
import org.aidiary.dto.UserDTO;
import org.aidiary.entity.User;
import org.aidiary.service.UserService;
import org.aidiary.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.aidiary.config.JwtProperties;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;  // JwtUtil을 DI로 주입
    private final JwtProperties jwtProperties;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok("Registration successful!");
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            User user = userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
            if (user == null) {
                return ResponseEntity.status(401).body("Login failed: Invalid email or password");
            }
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("token", "Bearer " + token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("유효하지 않은 인증 헤더입니다.");
            }

            String token = authHeader.substring(7);

            // 로그아웃 처리 (클라이언트 측에서 토큰 삭제로 처리)
            return ResponseEntity.ok("로그아웃 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그아웃 실패: " + e.getMessage());
        }
    }


}
