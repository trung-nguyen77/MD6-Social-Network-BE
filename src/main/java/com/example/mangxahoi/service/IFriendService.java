package com.example.mangxahoi.service;

import com.example.mangxahoi.model.Friend;
import com.example.mangxahoi.model.User;

import java.util.List;

public interface IFriendService {
    List<Friend> findAll();
    List<User> getListNotAddFriend(Long idUser1);
    List<User> getListAddedFriend(Long idUser1);
    List<User> getListWaitMakeFriend(Long idUser1);
    List<User> listMutualFriend(Long idUser1,Long idUser2);
    void save(Long idUser1, Long idUser2);
    void setFriend(Long idFriend);
    void deleteAddedFriend(Long idUser,Long idFriend);
    void deleteWaitAddFriend(Long idUser,Long idFriend);
}
