package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date date_Comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
