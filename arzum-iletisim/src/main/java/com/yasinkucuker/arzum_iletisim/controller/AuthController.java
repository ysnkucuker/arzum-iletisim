package com.yasinkucuker.arzum_iletisim.controller;

import com.yasinkucuker.arzum_iletisim.model.User;
import com.yasinkucuker.arzum_iletisim.repository.UserRepository;
import com.yasinkucuker.arzum_iletisim.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        // Kullanıcıyı DB'den al
        User dbUser = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // Şifre kontrol
        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Şifre yanlış");
        }

        // Token oluştur
        String token = jwtUtil.generateToken(dbUser.getUsername());

        return ResponseEntity.ok(token);
    }
}