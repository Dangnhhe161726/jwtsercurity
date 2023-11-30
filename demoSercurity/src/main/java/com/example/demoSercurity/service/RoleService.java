package com.example.demoSercurity.service;

import com.example.demoSercurity.entity.Role;
import com.example.demoSercurity.repository.RoleRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface RoleService {
    Role createNewRole(Role role);
}
