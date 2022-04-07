package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface INotificationRepo extends JpaRepository<Notification, Long> {
}
