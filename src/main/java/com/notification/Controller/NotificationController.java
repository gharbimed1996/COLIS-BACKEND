package com.notification.Controller;
import com.notification.entities.Notifications;
import com.notification.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")

@RestController
@RequestMapping("/notif")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping("/all")
    @ResponseBody

    public List<Notifications> announces(){
        return notificationRepository.findAll();
    }
}