package com.example.newauth.service;

import com.example.newauth.domain.Role;
import com.example.newauth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}