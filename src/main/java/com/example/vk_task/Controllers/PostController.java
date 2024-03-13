package com.example.vk_task.Controllers;

import com.example.vk_task.Entities.Post;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import com.example.vk_task.Services.AuditService;
import com.example.vk_task.Services.EntityServices.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@PreAuthorize("hasAuthority('ROLE_POSTS') or hasAuthority('ROLE_ADMIN')")
public class PostController {
    private final String controllerMapping;
    private final PostService postService;
    private final AuditService auditService;
    private final CacheServiceInterface<Post> cacheService;

    public PostController(PostService postService, AuditService auditService, CacheServiceInterface<Post> cacheService) {
        this.postService = postService;
        this.auditService = auditService;
        this.cacheService = cacheService;
        this.controllerMapping = "/api/posts";
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable("postId") Long postId) {
        auditService.logMessage("YES", controllerMapping + "/" + postId, "GET");

        if (cacheService.contains(postId)) {
            return cacheService.get(postId);
        } else {
            Post post = postService.get(postId);
            cacheService.put(postId, post);

            return post;
        }
    }

    @PostMapping("")
    public String createPost(@RequestBody Post post) {
        if (cacheService.post(post)) {
            auditService.logMessage("YES", controllerMapping, "POST");
            return "Nice";
        } else {
            auditService.logMessage("NO", controllerMapping, "POST");
            return "Entity already exist!";
        }
    }

    @PutMapping("/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @RequestBody Post post) {
        auditService.logMessage("YES", controllerMapping + "/" + postId, "PUT");

        cacheService.put(postId, post);
        postService.put(postId, post);

        return "Nice";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        auditService.logMessage("YES", controllerMapping + "/" + postId, "DELETE");

        cacheService.delete(postId);
        postService.delete(postId);

        return "Nice";
    }
}
