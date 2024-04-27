package com.example.group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJpaRepository extends JpaRepository<GroupJpaEntity, Long> {
    GroupJpaEntity findByGroupId(Long groupId);
}
