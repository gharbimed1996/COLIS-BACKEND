package com.notification.repositories;


import com.notification.entities.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notifications,String> {
}
