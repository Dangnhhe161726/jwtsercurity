package com.example.demoSercurity.Sercurity;

import com.example.demoSercurity.entity.Role;
import com.example.demoSercurity.entity.UserEntity;
import com.example.demoSercurity.repository.UserRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRespository userRespository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRespository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        return new User(userEntity.getEmail(), userEntity.getPassword(), mapRoles(userEntity.getRoles()));
    }

    private Collection<GrantedAuthority> mapRoles(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
