package com.example.vk_task.Services.EntityServices;

import com.example.vk_task.Entities.Album;
import com.example.vk_task.Interfaces.EntityServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlbumService implements EntityServiceInterface<Album> {
    private final String url;
    private final RestTemplate restTemplate;

    public AlbumService() {
        url = "https://jsonplaceholder.typicode.com/albums/";
        restTemplate = new RestTemplate();
    }

    public Album get(Long id) {
        return restTemplate.getForEntity(url + id, Album.class).getBody();
    }

    public void post(Album album) {
        restTemplate.postForEntity(url, album, Album.class);
    }

    public void put(Long id, Album album) {
        restTemplate.put(url + id, album);
    }

    public void delete(Long id) {
        restTemplate.delete(url + id);
    }
}
