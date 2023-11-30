package com.example.demoSercurity.service;

import com.example.demoSercurity.dto.AuthResponseDto;
import com.example.demoSercurity.dto.LoginDto;
import com.example.demoSercurity.dto.RegisterDto;

public interface AuthService {

    AuthResponseDto login (LoginDto loginDto);
    AuthResponseDto register(RegisterDto registerDto);
}
