package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.User;
import com.example.mangxahoi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserCtrl {
    @Autowired
    IUserService userService;

    @GetMapping("/listUser")
    public ResponseEntity<List<User>> getListUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/detailUser/{id}")
    public ResponseEntity<User> detailUser(@PathVariable long id) {
        return new ResponseEntity<>(userService.findUserByID(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<User>> findUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.findUserByUsername(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/showUserByRole")
    public ResponseEntity<List<User>> findUserByRole(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserByRole(id), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<User> edit(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
