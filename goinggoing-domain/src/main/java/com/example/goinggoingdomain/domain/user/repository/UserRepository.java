package com.example.goinggoingdomain.domain.user.repository;

import com.example.goinggoingdomain.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
    User findByUserEmail(String userEmail);
}
