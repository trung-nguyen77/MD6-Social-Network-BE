package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IPostRepo extends JpaRepository<Post, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM mangxahoi.post order by date_post DESC")
    List<Post> findPostByTime();

    @Query(nativeQuery = true, value = "select * from mangxahoi.post where post.users_id =:users_id ")
    Page<Post> showPostByUserId(@Param("users_id") Long users_id, Pageable pageable);

}
