package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Comment;
import com.example.mangxahoi.service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentCtrl {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping("")
    public ResponseEntity<List<Comment>> getListCommentByPostID(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findListCommentByPostID(id), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> edit(@PathVariable Long id, @RequestBody Comment comment) {
        comment.setId(id);
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
