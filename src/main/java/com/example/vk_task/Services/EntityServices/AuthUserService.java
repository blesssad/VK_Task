package com.example.vk_task.Services.EntityServices;


import com.example.vk_task.Entities.AuthUser;
import com.example.vk_task.Repositories.AuthUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserService(AuthUserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(AuthUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @PostConstruct
    public void addDefaultUsers() {
        if (!repository.existsByName("admin")) {
            AuthUser admin = new AuthUser();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles("ROLE_ADMIN");
            repository.save(admin);
        }

        if (!repository.existsByName("users")) {
            AuthUser admin = new AuthUser();
            admin.setName("users");
            admin.setPassword(passwordEncoder.encode("users"));
            admin.setRoles("ROLE_USERS");
            repository.save(admin);
        }

        if (!repository.existsByName("posts")) {
            AuthUser admin = new AuthUser();
            admin.setName("posts");
            admin.setPassword(passwordEncoder.encode("posts"));
            admin.setRoles("ROLE_POSTS");
            repository.save(admin);
        }

        if (!repository.existsByName("albums")) {
            AuthUser admin = new AuthUser();
            admin.setName("albums");
            admin.setPassword(passwordEncoder.encode("albums"));
            admin.setRoles("ROLE_ALBUMS");
            repository.save(admin);
        }
    }
}
