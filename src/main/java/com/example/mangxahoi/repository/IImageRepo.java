package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IImageRepo extends JpaRepository<Image, Long> {
    @Query(value = "select * from image where image.post_id = :id", nativeQuery = true)
    List<Image> findListImgByPostId(@Param("id") Long id);
}
