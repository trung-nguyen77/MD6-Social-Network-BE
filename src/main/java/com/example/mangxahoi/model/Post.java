package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime datePost;
    private int countLike;
    private String status;

    @ManyToOne
    User user;


    public Post() {
    }

    public Post(Long id, String content, LocalDateTime datePost, int countLike, String status, User user) {
        this.id = id;
        this.content = content;
        this.datePost = datePost;
        this.countLike = countLike;
        this.status = status;
        this.user = user;
    }
}
