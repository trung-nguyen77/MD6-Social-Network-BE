package com.example.mangxahoi.service;

import com.example.mangxahoi.model.User;

import java.util.List;

public interface ILikeService {
    List<User> listUserLikePost(Long id);
    List<User> listUserLikeComment(Long id);
    void createLikePost(Long idUser,Long idPost);
    void createLikeComment(Long idUser,Long idComment);
    void delete(Long idLike);
    int countLikePost(Long idPost);
    int countLikeComment(Long idComment);
}
