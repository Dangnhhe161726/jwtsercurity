package com.example.demoSercurity.service.impl;

import com.example.demoSercurity.entity.Role;
import com.example.demoSercurity.repository.RoleRespository;
import com.example.demoSercurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRespository roleRespository;


    @Override
    public Role createNewRole(Role role) {
        return roleRespository.save(role);
    }
}
