package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

    @ManyToOne
    User user;

    @ManyToOne
    Post post;

    public void setId(Long id) {
        this.id = id;
    }
}

