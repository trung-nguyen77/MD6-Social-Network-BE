package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.dto.postImgdto.PostImgdto;
import com.example.mangxahoi.model.Post;
import com.example.mangxahoi.repository.IPostRepo;
import com.example.mangxahoi.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostRepo postRepo;

    @Autowired
    ImageServiceImpl imageService;


    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public PostImgdto findById(Long id) {
        Post post =  postRepo.findById(id).get();
        PostImgdto postDto = new PostImgdto(post.getId(), post.getContent(),post.getStatus(), post.getDatePost(), post.getCountLike(), post.getUser()
                , imageService.findListImgByPostId(post.getId()));
        return postDto;
    }

    @Override
    public List<PostImgdto> findPostByTime() {
        List<Post> posts = postRepo.findPostByTime();
        List<PostImgdto> allPostDtos = new ArrayList<>();

        for (Post post : posts) {
            PostImgdto postDto = new PostImgdto(post.getId(), post.getContent(),post.getStatus(), post.getDatePost(), post.getCountLike(), post.getUser()
                    , imageService.findListImgByPostId(post.getId()));
            allPostDtos.add(postDto);
        }
        return allPostDtos;
    }

    @Override
    public Post findPostByPostDto(Long id) {
        return postRepo.findById(id).get();
    }

    @Override
    public Page<Post> showPostByUserId(Long id, Pageable pageable) {
        return postRepo.showPostByUserId(id, pageable);
    }

}
