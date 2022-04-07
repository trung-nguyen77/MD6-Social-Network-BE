package com.example.mangxahoi.service;

import com.example.mangxahoi.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> listNotification();
    void createFriendRequest(Long idUser1, Long idUser2);
    void notificationReceive(Long id);
    void deleteNotification(Long id);
}
