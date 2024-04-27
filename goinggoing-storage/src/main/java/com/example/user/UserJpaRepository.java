package com.example.user;

import com.example.user.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    UserJpaEntity findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
