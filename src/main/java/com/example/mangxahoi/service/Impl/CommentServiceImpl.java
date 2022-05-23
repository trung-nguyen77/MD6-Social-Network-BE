package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Comment;
import com.example.mangxahoi.repository.ICommentRepo;
import com.example.mangxahoi.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentRepo commentRepo;

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comment> findListCommentByPostID(Long id) {
        return commentRepo.findListCommentByPostID(id);
    }
}
