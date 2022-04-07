package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ILikeRepo extends JpaRepository<Like, Long> {
    // đếm số like post
    @Query(nativeQuery = true,value = "select count(post_id) from likes where post_id =:idPost")
    Integer countLikesPost (@Param(value = "idPost")Long idPost);

    // đếm số like comment
    @Query(nativeQuery = true,value = "select count(comment_id) from likes where post_id =:idComment")
    Integer countLikesComment (@Param(value = "idComment")Long idComment);

}
