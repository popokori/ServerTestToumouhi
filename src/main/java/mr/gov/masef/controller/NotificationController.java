package mr.gov.masef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mr.gov.masef.entites.Notification;
import mr.gov.masef.service.NotificationService;
import mr.gov.masef.service.WebSocketNotificationService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private WebSocketNotificationService webSocketNotificationService;

    /*  @PostMapping("/send")
    public Notification sendNotification(@RequestParam String numTelephone, @RequestBody Notification notification) {
        // Sauvegarder la notification dans la base de données
    	
        Notification savedNotification = notificationService.addNotification(numTelephone, notification.getMessage(), notification.getUrl());
        // Envoyer la notification via WebSocket
        webSocketNotificationService.sendNotificationToUser(numTelephone, notification.getMessage(),notification.getUrl(),ref);
        return savedNotification;
    }*/
    // Récupérer les notifications non lues de l'utilisateur connecté
    @GetMapping("/notifications")
    public List<Notification> getNotifications(@RequestParam(name = "numTelephone", required = true) String numTel) {
        // Récupérer le `numTelephone` depuis le token JWT
        return notificationService.getUnreadNotifications(numTel);
    }

    // Marquer toutes les notifications comme lues pour l'utilisateur connecté
    @PostMapping("/markAllAsRead")
    public void markAllAsRead(@RequestBody String numTel) {
        System.out.println(numTel);
        notificationService.markAllAsRead(numTel);
    }
    
    @PostMapping("/markIsRead")
    public void markIsRead(@RequestBody long ref) {
        System.out.println(ref);
        notificationService.markIsRead(ref);
    }

}