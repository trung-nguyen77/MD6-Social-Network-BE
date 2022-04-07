package com.example.mangxahoi.service;

import com.example.mangxahoi.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> findAll();
    void save(Image image);
    void delete(Long id);
    List<Image> findListImgByPostId(Long id);
}
