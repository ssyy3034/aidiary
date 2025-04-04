package org.aidiary.controller;

import jakarta.validation.Valid;
import org.aidiary.dto.UserDTO;
import org.aidiary.entity.User;
import org.aidiary.service.UserService;
import org.aidiary.util.JwtUtil;
import org.aidiary.dto.UpdateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        try {
            // Authorization 헤더 확인
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(400).body("Invalid Authorization header");
            }

            // 토큰에서 이메일 추출
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(401).body("Invalid or expired token");
            }

            // 사용자 정보 조회
            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }

            // 조회 성공
            return ResponseEntity.ok(user);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid token format");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserInfo(HttpServletRequest request, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("유효하지 않은 인증 헤더입니다.");
            }
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            User user = userService.findByEmail(email);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
            }

            user.setName(updateUserDTO.getName());

            userService.updateUser(user);
            return ResponseEntity.ok("사용자 정보가 성공적으로 업데이트되었습니다."+user.getName());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류: " + e.getMessage());
        }

    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(400).body("유효하지 않은 인증 헤더입니다.");
            }
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            userService.deleteUser(email);
            return ResponseEntity.ok("사용자 계정이 성공적으로 삭제되었습니다.");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }


}
