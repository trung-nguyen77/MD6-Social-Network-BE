package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comment where post_id = :id", nativeQuery = true)
    List<Comment> findListCommentByPostID(@Param("id") Long id);
}
