package com.example.mangxahoi.controller;

import com.example.mangxahoi.dto.postImgdto.PostImgdto;
import com.example.mangxahoi.model.CheckDate;
import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.model.Post;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.service.IImageService;
import com.example.mangxahoi.service.IPostService;
import com.example.mangxahoi.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostCtrl {

    @Autowired
    IPostService postService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    IImageService imageService;

    @GetMapping("/listPost")
    public ResponseEntity<Page<Post>> getAllPosts(@RequestParam(defaultValue = "0") int pageNumber) {
        return new ResponseEntity<>(postService.findAll(PageRequest.of(pageNumber, 5)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostImgdto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @GetMapping("showPost/{id}")
    public ResponseEntity<?> findPostByUserID(@RequestParam(defaultValue = "0") int pageNumber, @PathVariable Long id) {
        return new ResponseEntity<>(postService.showPostByUserId(id, (PageRequest.of(pageNumber, 5))), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody PostImgdto post) {
        User user = userService.findUserByID(post.getUser().getId());
        post.setDate_Post(CheckDate.getTimePost());
        post.setUser(user);
        Post postNew = PostImgdto.bulldPost(post);
        postService.save(postNew);
        for (Image img : post.getListImage()) {
            img.setUser(post.getUser());
            img.setPost(postNew);
            imageService.save(img);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PostImgdto> edit(@RequestBody PostImgdto postImgdto) {
        Post post = postService.findPostByPostDto(postImgdto.getId());
        post.setContent(postImgdto.getContent());
        post.setCountLike(postImgdto.getCountLike());
        CheckDate checkDate = new CheckDate();
        post.setDatePost(checkDate.getTimePost());
        post.setUser(postImgdto.getUser());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
