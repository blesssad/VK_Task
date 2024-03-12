package com.example.vk_task.Services;

import com.example.vk_task.Config.CustomAuthUserDetails;
import com.example.vk_task.Entities.AuthUser;
import com.example.vk_task.Repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomAuthUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = repository.findByName(username);

        return user.map(CustomAuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }
}
