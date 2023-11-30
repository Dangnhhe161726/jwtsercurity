package com.example.demoSercurity.service.impl;

import com.example.demoSercurity.entity.UserEntity;
import com.example.demoSercurity.repository.UserRespository;
import com.example.demoSercurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;
    @Override
    public UserEntity registerNewUser(UserEntity userEntity) {
        return userRespository.save(userEntity);
    }
}
