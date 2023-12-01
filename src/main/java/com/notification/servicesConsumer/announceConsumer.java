package com.notification.servicesConsumer;

import com.example.commonApi.events.AnnounceCreatedEvent;
import com.notification.entities.Notifications;
import com.notification.repositories.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class announceConsumer {
    @Autowired
    NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(announceConsumer.class);

    @KafkaListener(topics = "announce_topic",groupId = "${spring.kafka.consumer.group-id}")

    public void consume(AnnounceCreatedEvent event){
        LOGGER.info(event.getId().toString());
        Notifications notifications = new Notifications();
        notifications.setMessage("announce ajouté");
        notifications.setUsername(event.getId().toString());
        notificationRepository.save(notifications);
    }

}
