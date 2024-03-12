package com.example.vk_task.Controllers;

import com.example.vk_task.Entities.AuthUser;
import com.example.vk_task.Services.EntityServices.AuthUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newUser")
public class AuthUserController {

    private final AuthUserService service;
    public AuthUserController(AuthUserService service){

        this.service = service;
    }

    @PostMapping("")
    public String createAlbum(@RequestBody AuthUser user) {
        service.addUser(user);

        return "Nice";
    }

}
