package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Like;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.repository.ICommentRepo;
import com.example.mangxahoi.repository.ILikeRepo;
import com.example.mangxahoi.repository.IPostRepo;
import com.example.mangxahoi.repository.IUserRepo;
import com.example.mangxahoi.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements ILikeService {
    @Autowired
    IUserRepo userRepo;

    @Autowired
    ILikeRepo likeRepo;

    @Autowired
    IPostRepo postRepo;

    @Autowired
    ICommentRepo commentRepo;

    @Override
    public List<User> listUserLikePost(Long id) {
        return userRepo.listUserLikePost(id);
    }

    @Override
    public List<User> listUserLikeComment(Long id) {
        return userRepo.listUserLikeComment(id);
    }

    @Override
    public void createLikePost(Long idUser, Long idPost) {
        Like like = new Like(userRepo.findById(idUser).get(),postRepo.findById(idPost).get());
        likeRepo.save(like);
    }

    @Override
    public void createLikeComment(Long idUser, Long idComment) {
        Like like = new Like(userRepo.findById(idUser).get(),commentRepo.findById(idComment).get());
        likeRepo.save(like);
    }

    @Override
    public void delete(Long id) {
        likeRepo.deleteById(id);
    }

    @Override
    public int countLikePost(Long idPost) {
        return likeRepo.countLikesPost(idPost);
    }

    @Override
    public int countLikeComment(Long idComment) {
        return likeRepo.countLikesComment(idComment);
    }

}
