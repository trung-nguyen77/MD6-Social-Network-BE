package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.repository.IImageRepo;
import com.example.mangxahoi.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    IImageRepo imageRepo;
    @Override
    public List<Image> findAll() {
        return imageRepo.findAll();
    }

    @Override
    public void save(Image image) {
        imageRepo.save(image);
    }

    @Override
    public void delete(Long id) {
        imageRepo.deleteById(id);
    }

    @Override
    public List<Image> findListImgByPostId(Long id) {
        return imageRepo.findListImgByPostId(id);
    }
}
