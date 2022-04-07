package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Comment comment;

    public Like() {
    }

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Like(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }


}
