package com.example.vk_task.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Album {
    private int userId;
    private int id;
    private String title;
}
