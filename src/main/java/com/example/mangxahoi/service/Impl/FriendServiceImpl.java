package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Friend;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.repository.IFriendRepo;
import com.example.mangxahoi.repository.IUserRepo;
import com.example.mangxahoi.service.IFriendService;
import com.example.mangxahoi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
@Service
public class FriendServiceImpl implements IFriendService {

    @Autowired
    IFriendRepo friendRepo;

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IUserService userService;

    @Override
    public List<Friend> findAll() {
        return friendRepo.findAll();
    }

    @Override
    public List<User> getListNotAddFriend(Long idUser1) {
        List<User> userList = userService.findAll();
        List<Friend> getList = friendRepo.listNotAddFriend(idUser1);
        List<Long> getId = new ArrayList<>();
        getId.add(idUser1);
        for (int i = 0; i < getList.size(); i++) {
            if (getList.get(i).getUser1().getId() == idUser1) {
                getId.add(getList.get(i).getUser2().getId());
            }
            if (getList.get(i).getUser2().getId() == idUser1) {
                getId.add(getList.get(i).getUser1().getId());
            }
        }
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < getId.size(); j++) {
                if (userList.get(i).getId() == getId.get(j)) {
                    userList.remove(i);
                }
            }
        }
        return userList;
    }

    @Override
    public List<User> getListAddedFriend(Long idUser1) {
        return userRepo.listAddedFriend(idUser1);
    }

    @Override
    public List<User> getListWaitMakeFriend(Long idUser1) {
        return  userRepo.listWaitMakeFriend(idUser1);
    }

    @Override
    public List<User> listMutualFriend(Long idUser1, Long idUser2) {
        return userRepo.listMutualFriend(idUser1, idUser2);
    }

    @Override
    public void save(Long idUser1, Long idUser2) {
        Friend friend = new Friend(false, LocalDateTime.now(), userService.findUserByID(idUser1), userService.findUserByID(idUser2));
        friendRepo.save(friend);
    }

    @Override
    public void setFriend(Long idFriend) {
        Friend friend = friendRepo.findById(idFriend).get();
        friend.setStatus(true);
        friendRepo.save(friend);
    }

    @Override
    public void deleteAddedFriend(Long idUser, Long idFriend) {
        friendRepo.delete(friendRepo.findAddedFriendById(idUser,idFriend));
    }

    @Override
    public void deleteWaitAddFriend(Long idUser, Long idFriend) {
        friendRepo.delete(friendRepo.findWaitAddFriendById(idUser, idFriend));
    }

}
