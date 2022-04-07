package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageCtrl {
    @Autowired
    IImageService imageService;

    @GetMapping("/listImg")
    public ResponseEntity<List<Image>> getList(){
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createImg")
    public ResponseEntity<Image> create(@RequestBody Image image){
        imageService.save(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/editImg/{id}")
    public ResponseEntity<Image> edit(@RequestBody Image image,@PathVariable Long id){
        image.setId(id);
        imageService.save(image);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @DeleteMapping("/deleteImg/{id}")
    public void deleteImg(@PathVariable Long id){
        imageService.delete(id);
    }
}
