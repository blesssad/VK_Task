package com.example.vk_task.Controllers;

import com.example.vk_task.Entities.Album;
import com.example.vk_task.Interfaces.CacheServiceInterface;
import com.example.vk_task.Services.EntityServices.AlbumService;
import com.example.vk_task.Services.AuditService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
@PreAuthorize("hasAuthority('ROLE_ALBUMS')")
public class AlbumController {
    private final String controllerMapping;
    private final AlbumService albumService;
    private final AuditService auditService;
    private final CacheServiceInterface<Album> cacheService;

    public AlbumController(AlbumService albumService, AuditService auditService, CacheServiceInterface<Album> cacheService) {
        this.albumService = albumService;
        this.auditService = auditService;
        this.cacheService = cacheService;
        this.controllerMapping = "/api/albums";
    }

    @GetMapping("/{albumId}")
    public Album getAlbum(@PathVariable("albumId") Long albumId) {
        auditService.logMessage("YES", controllerMapping + "/" + albumId, "GET");

        if (cacheService.contains(albumId)) {
            return cacheService.get(albumId);
        } else {
            Album album = albumService.get(albumId);
            cacheService.put(albumId, album);

            return album;
        }
    }

    @PostMapping("")
    public String createAlbum(@RequestBody Album album) {
        auditService.logMessage("YES", controllerMapping, "POST");

        cacheService.put(album.getId(), album);
        albumService.post(album);

        return "Nice";
    }

    @PutMapping("/{albumId}")
    public String updateAlbum(@PathVariable("albumId") Long albumId, @RequestBody Album album) {
        auditService.logMessage("YES", controllerMapping + "/" + albumId, "PUT");

        cacheService.put(albumId, album);
        albumService.put(albumId, album);

        return "Nice";
    }

    @DeleteMapping("/{albumId}")
    public String deleteAlbum(@PathVariable("albumId") Long albumId) {
        auditService.logMessage("YES", controllerMapping + "/" + albumId, "DELETE");

        cacheService.delete(albumId);
        albumService.delete(albumId);

        return "Nice";
    }
}