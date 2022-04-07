package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Like;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.service.Impl.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping()
public class LikeCtrl {
    @Autowired
    LikeServiceImpl likeService;

    // like bài post
    @GetMapping("/post/{idUser}/{idPost}")
    public ResponseEntity<Like> likePost(@PathVariable Long idUser, @PathVariable Long idPost){
        likeService.createLikePost(idUser,idPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // like comment
    @GetMapping("/comment/{idUser}/{idComment}")
    public ResponseEntity<Like> likeComment(@PathVariable Long idUser,@PathVariable Long idComment){
        likeService.createLikeComment(idUser,idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xóa like post or comment
    @DeleteMapping("/delete/{idLike}")
    public ResponseEntity<Like> deleteLike(@PathVariable Long idLike){
        likeService.delete(idLike);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem số lượt like bài post
    @GetMapping("/countLikePost/{idPost}")
    public ResponseEntity<Integer> countLikePost(@PathVariable Long idPost){
        return new ResponseEntity<>(likeService.countLikePost(idPost),HttpStatus.OK);
    }

    // xem số lượt like comment
    @GetMapping("/countLikePost/{idComment}")
    public ResponseEntity<Integer> countLikeComment(@PathVariable Long idComment){
        likeService.countLikeComment(idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem danh sách lượt like bài post
    @GetMapping("/listLikePost/{idPost}")
    public ResponseEntity<List<User>> listLikePost(@PathVariable Long idPost){
        return new ResponseEntity<>(likeService.listUserLikePost(idPost),HttpStatus.OK);
    }

    // xem danh sách lượt like comment
    @GetMapping("/listLikeComment/{idComment}")
    public ResponseEntity<List<User>> listLikeComment(@PathVariable Long idComment){
        return new ResponseEntity<>(likeService.listUserLikeComment(idComment),HttpStatus.OK);
    }
}
