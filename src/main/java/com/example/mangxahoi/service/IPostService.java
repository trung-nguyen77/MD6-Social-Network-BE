package com.example.mangxahoi.service;

import com.example.mangxahoi.dto.postImgdto.PostImgdto;
import com.example.mangxahoi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
    Post save(Post post);
    void delete(Long id);
    Page<Post> findAll(Pageable pageable);
    PostImgdto findById(Long id);
    List<PostImgdto> findPostByTime();
    Post findPostByPostDto(Long id);
    Page<Post> showPostByUserId(Long id, Pageable pageable);
}
