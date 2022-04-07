package com.example.mangxahoi.service.Impl;

import com.example.mangxahoi.model.Notification;
import com.example.mangxahoi.model.User;
import com.example.mangxahoi.repository.INotificationRepo;
import com.example.mangxahoi.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    INotificationRepo notificationRepo;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<Notification> listNotification() {
        return notificationRepo.findAll();
    }

    @Override
    public void createFriendRequest(Long idUser1, Long idUser2) {
        String nameSender = "";
        for (User user: userService.findAll()) {
            if (user.getId() == idUser1){
                nameSender = user.getName();
            }
        }
        Notification notification = new Notification(LocalDateTime.now(),"Bạn có lời mời kết bạn từ " + nameSender, userService.findUserByID(idUser1), userService.findUserByID(idUser2));
        notificationRepo.save(notification);
    }

    @Override
    public void notificationReceive(Long id) {
        String nameReceive = "";
        Notification notification = new Notification();
        for (Notification no:listNotification()) {
            if (no.getId() == id){
                nameReceive = no.getTo().getName();
                notification.setSince(LocalDateTime.now());
                notification.setContent(nameReceive+ " đã chấp nhận lời mời kết bạn của bạn." );
            }
        }
        notificationRepo.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }
}
