package com.example.demoSercurity.repository;

import com.example.demoSercurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface RoleRespository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
