package com.example.mangxahoi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    // 0 : false : đang gửi lời mời kb
    // 1 : true : đã là bạn bè
    private LocalDateTime since;

    @ManyToOne
    User User1, User2;


    public Friend() {
    }

    public Friend(boolean status, LocalDateTime since, User user1, User user2) {
        this.status = status;
        this.since = since;
        User1 = user1;
        User2 = user2;
    }

}
