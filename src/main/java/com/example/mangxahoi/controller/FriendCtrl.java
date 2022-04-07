package com.example.mangxahoi.controller;

import com.example.mangxahoi.model.Friend;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.service.IFriendService;
import com.example.mangxahoi.service.INotificationService;
import com.example.mangxahoi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Friend")
public class FriendCtrl {
    @Autowired
    IFriendService friendService;

    @Autowired
    IUserService userService;

    @Autowired
    INotificationService notificationService;

    @GetMapping("/search_friend/{name}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String name) {
        return new ResponseEntity<>(userService.findUserByUsername(name), HttpStatus.OK);
    }

    // xem danh sách bạn bè chưa kết bạn
    @GetMapping("/notAddFriend/{idUser}")
    public ResponseEntity<List<User>> notAddFriend(@PathVariable Long idUser) {
        return new ResponseEntity<>(friendService.getListNotAddFriend(idUser), HttpStatus.OK);
    }

    // xem danh sách bạn bè chờ kết bạn
    @GetMapping("/listWaitMakeFriend/{idUser}")
    public ResponseEntity<List<User>> listWaitMakeFriend(@PathVariable Long idUser) {
        return new ResponseEntity<>(friendService.getListWaitMakeFriend(idUser), HttpStatus.OK);
    }

    // hủy  kết bạn
    @DeleteMapping("/deleteFriend/{idUser}/{idFriend}")
    public ResponseEntity deleteFriend(@PathVariable Long idUser, @PathVariable Long idFriend) {
        friendService.deleteAddedFriend(idUser, idFriend);
        return new ResponseEntity(HttpStatus.OK);
    }

    // hủy yêu cầu kết bạn
    @DeleteMapping("/deleteWaitFriend/{idUser}/{idFriend}")
    public ResponseEntity deleteWaitFriend(@PathVariable Long idUser, @PathVariable Long idFriend) {
        friendService.deleteWaitAddFriend(idUser, idFriend);
        return new ResponseEntity(HttpStatus.OK);
    }

    // xem danh sách bạn bè đã kết bạn
    @GetMapping("/addedFriend/{idUser}")
    public ResponseEntity<List<User>> addedFriend(@PathVariable Long idUser) {
        return new ResponseEntity<>(friendService.getListAddedFriend(idUser), HttpStatus.OK);
    }

    // gửi yêu cầu kết bạn
    @GetMapping("/waitMakeFriend/{idUser1}/{idUser2}")
    public ResponseEntity<Friend> waitMakeFriend(@PathVariable Long idUser1, @PathVariable Long idUser2) {
        friendService.save(idUser1, idUser2);
        notificationService.createFriendRequest(idUser1, idUser2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // đồng ý kết bạn
    @PutMapping("/agreeMakeFriend/{idFriend}/{idNotif}")
    public ResponseEntity<Friend> agreeMakeFriend(@PathVariable Long idFriend, @PathVariable Long idNotif) {
        friendService.setFriend(idFriend);
        notificationService.deleteNotification(idNotif);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // xem danh sách các bạn bè chung
    @GetMapping("/listMutualFriends/{idUser1}/{idUser2}")
    public ResponseEntity<List<User>> listMutualFriends(@PathVariable Long idUser1, @PathVariable Long idUser2) {
        return new ResponseEntity<>(friendService.listMutualFriend(idUser1, idUser2), HttpStatus.OK);
    }
}
