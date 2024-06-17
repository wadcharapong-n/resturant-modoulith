package com.north.restaurant.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @ApplicationModuleListener
    public void sendNotification(NotificationDTO event) {
        log.info("send notification title: {}, message : {}", event.title(), event.message());
    }

    @ApplicationModuleListener
    public void sendNotificationFake(NotificationDTO event) {
        log.info("send fake notification title: {}, message : {}", event.title(), event.message());
    }
}
