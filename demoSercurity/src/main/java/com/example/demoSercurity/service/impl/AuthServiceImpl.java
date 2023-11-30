package com.example.demoSercurity.service.impl;

import com.example.demoSercurity.Sercurity.JwtGenerator;
import com.example.demoSercurity.dto.AuthResponseDto;
import com.example.demoSercurity.dto.LoginDto;
import com.example.demoSercurity.dto.RegisterDto;
import com.example.demoSercurity.entity.Role;
import com.example.demoSercurity.entity.UserEntity;
import com.example.demoSercurity.repository.RoleRespository;
import com.example.demoSercurity.repository.UserRespository;
import com.example.demoSercurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRespository userRespository;
    private final RoleRespository roleRespository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var user = userRespository.findByEmail(loginDto.getEmail())
                .orElseThrow();
        var token = jwtGenerator.generateToken(user.getEmail());

        return AuthResponseDto
                .builder()
                .accessToken(token)
                .build();
    }

    @Override
    public AuthResponseDto register(RegisterDto registerDto) {
        Optional<UserEntity> userResponse = userRespository.findByEmail(registerDto.getEmail());
        if (userResponse.isPresent()) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userEntity.setFullName(registerDto.getFullName());
        Role roles = roleRespository.findByName(registerDto.getRole().getName()).get();
//        if(roles == null) {
//             roles = roleRespository.findByName("CUSTOMER").get();
//        }

        userEntity.setRoles(Collections.singletonList(roles));
        userRespository.save(userEntity);
        var token = jwtGenerator.generateToken(userEntity.getEmail());

        return AuthResponseDto
                .builder()
                .accessToken(token)
                .build();
    }
}
