package com.example.vk_task.Services.EntityServices;

import com.example.vk_task.Entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private final String url;
    private final RestTemplate restTemplate;

    public UserService() {
        url = "https://jsonplaceholder.typicode.com/users/";
        restTemplate = new RestTemplate();
    }

    public User get(Long id) {
        return restTemplate.getForEntity(url + id, User.class).getBody();
    }

    public void post(User user) {
        restTemplate.postForEntity(url, user, User.class);
    }

    public void put(Long id, User user) {
        restTemplate.put(url + id, user);
    }

    public void delete(Long id) {
        restTemplate.delete(url + id);
    }
}
