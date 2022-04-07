package com.example.mangxahoi.service;

import com.example.mangxahoi.model.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);
    void delete(Long id);
    List<Comment> findListCommentByPostID(Long id);
}
