package com.example.vk_task.Repositories;

import com.example.vk_task.Entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByName(String name);
    boolean existsByName(String name);
}
