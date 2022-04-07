package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime since;
    private String content;

    @ManyToOne
    private User from;

    @ManyToOne
    private User to;

    public Notification() {
    }

    public Notification(LocalDateTime since, String content, User from, User to) {
        this.since = since;
        this.content = content;
        this.from = from;
        this.to = to;
    }
}
