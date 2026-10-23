package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.CustomerSiteDto;
import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.services.NotificationServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    private final NotificationServiceI notificationServiceI;

    public NotificationController(NotificationServiceI notificationServiceI) {
        this.notificationServiceI = notificationServiceI;
    }

    @PostMapping("/notification-add")
    public ResponseEntity<ApiListResponse<NotificationDto>> addNotification(@RequestBody NotificationDto notificationDto){
        NotificationDto saved = notificationServiceI.addNotification(notificationDto);
        return ResponseEntity.created(URI.create("/api/v1/notification-add/" + saved.getId()))
                .body(ApiListResponse.ofOne(saved));
    }

    @GetMapping("/notifications")
    public ResponseEntity<ApiListResponse<NotificationDto>> getAllNotifications(){
        return ResponseEntity.ok(ApiListResponse.of(notificationServiceI.getAllNotifications()));
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> getNotificationById(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(notificationServiceI.getNotificationById(id)));
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> updateNotification(
            @PathVariable Integer id,
            @RequestBody NotificationDto notificationDto){
        return ResponseEntity.ok(ApiListResponse.ofOne(notificationServiceI.updateNotification(id, notificationDto)));
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> deleteNotification(@PathVariable Integer id){
        return ResponseEntity.ok(ApiListResponse.ofOne(notificationServiceI.deleteNotification(id)));
    }
}
