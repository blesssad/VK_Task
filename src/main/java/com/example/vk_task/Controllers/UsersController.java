package com.example.vk_task.Controllers;

import com.example.vk_task.Entities.User;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import com.example.vk_task.Services.AuditService;
import com.example.vk_task.Services.EntityServices.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasAuthority('ROLE_USERS') or hasAuthority('ROLE_ADMIN')")
public class UsersController {
    private final String controllerMapping;
    private final UserService userService;
    private final AuditService auditService;
    private final CacheServiceInterface<User> cacheService;

    public UsersController(UserService userService, AuditService auditService, CacheServiceInterface<User> cacheService) {
        this.userService = userService;
        this.auditService = auditService;
        this.cacheService = cacheService;
        this.controllerMapping = "/api/users";
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        auditService.logMessage("YES", controllerMapping + "/" + userId, "GET");

        if (cacheService.contains(userId)) {
            return cacheService.get(userId);
        } else {
            User user = userService.get(userId);
            cacheService.put(userId, user);

            return user;
        }
    }

    @PostMapping("")
    public String createUser(@RequestBody User user) {
        if (cacheService.post(user)) {
            auditService.logMessage("YES", controllerMapping, "POST");
            return "Nice";
        } else {
            auditService.logMessage("NO", controllerMapping, "POST");
            return "Entity already exist!";
        }
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") Long userId, @RequestBody User user) {
        auditService.logMessage("YES", controllerMapping + "/" + userId, "PUT");

        cacheService.put(userId, user);
        userService.put(userId, user);

        return "Nice";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        auditService.logMessage("YES", controllerMapping + "/" + userId, "DELETE");

        cacheService.delete(userId);
        userService.delete(userId);

        return "Nice";
    }
}
