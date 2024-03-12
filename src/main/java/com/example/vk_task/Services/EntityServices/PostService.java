package com.example.vk_task.Services.EntityServices;

import com.example.vk_task.Entities.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {
    private final String url;
    private final RestTemplate restTemplate;

    public PostService() {
        url = "https://jsonplaceholder.typicode.com/posts/";
        restTemplate = new RestTemplate();
    }

    public Post get(Long id) {
        return restTemplate.getForEntity(url + id, Post.class).getBody();
    }

    public void post(Post post) {
        restTemplate.postForEntity(url, post, Post.class);
    }

    public void put(Long id, Post post) {
        restTemplate.put(url + id, post);
    }

    public void delete(Long id) {
        restTemplate.delete(url + id);
    }
}
