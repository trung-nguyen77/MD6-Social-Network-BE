package com.example.mangxahoi.dto.postImgdto;

import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.model.Post;
import com.example.mangxahoi.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostImgdto{
    private Long id;
    private String content;
    private String status;
    private LocalDateTime date_Post;
    private int countLike;

    User user;

    List<Image> listImage;

    static public Post bulldPost(PostImgdto post){
        return new Post(post.getId(), post.getContent(), post.getDate_Post(), post.getCountLike(), post.getContent(), post.getUser());
    }

    public PostImgdto(Long id, String content, String status, LocalDateTime date_Post, int countLike, User user, List<Image> listImage) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.date_Post = date_Post;
        this.countLike = countLike;
        this.user = user;
        this.listImage = listImage;

    }
}
