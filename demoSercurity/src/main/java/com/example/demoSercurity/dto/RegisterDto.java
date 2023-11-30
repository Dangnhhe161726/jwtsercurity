package com.example.demoSercurity.dto;

import com.example.demoSercurity.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String email;
    private String password;
    private String fullName;
    private Role role;
}
