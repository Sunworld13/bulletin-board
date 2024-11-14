package com.example.authentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthority extends JpaRepository<UserAuthority, Long> {
    Optional<UserAuthority> findByAuthority(String authority);
}
