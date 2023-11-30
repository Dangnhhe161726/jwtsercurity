package com.example.demoSercurity.controller;

import com.example.demoSercurity.dto.AuthResponseDto;
import com.example.demoSercurity.dto.LoginDto;
import com.example.demoSercurity.dto.RegisterDto;

import com.example.demoSercurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        AuthResponseDto authResponseDto = authService.login(loginDto);
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        AuthResponseDto authResponseDto = authService.register(registerDto);
        if(authResponseDto == null){
            return new ResponseEntity<>("Email is existed!",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
