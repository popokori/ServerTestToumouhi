package mr.gov.masef.service;
//src/main/java/com/example/service/NotificationService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Notification;
import mr.gov.masef.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {

 @Autowired
 private NotificationRepository notificationRepository;

 
 @Autowired
 private WebSocketNotificationService webSocketNotificationService;

 // Récupérer les notifications non lues pour un utilisateur
 public List<Notification> getUnreadNotifications(String numTelephone) {
     return notificationRepository.findByNumTelephoneOrderByRefDesc(numTelephone);
 }

 // Ajouter une nouvelle notification pour un utilisateur
 public Notification addNotification(String numTelephone, String message, String url) {
	 
     LocalDateTime now = LocalDateTime.now();

     // Formater la date au format "dd/MM/yyyy"
     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     String formattedDate = now.format(dateFormatter);

     // Formater l'heure au format "HH:mm"
     DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
     String formattedTime = now.format(timeFormatter);

     // Afficher la date et l'heure formatées
     System.out.println("Date maintenant : " + formattedDate);
     System.out.println("Heure maintenant : " + formattedTime);
     long ref=System.currentTimeMillis();
     Notification notification = new Notification(numTelephone, message, url,ref,formattedDate,formattedTime);
     webSocketNotificationService.sendNotificationToUser(numTelephone, notification.getMessage(),notification.getUrl(),ref,formattedDate,formattedTime);
     
     return notificationRepository.save(notification);
 }

 // Marquer toutes les notifications comme lues pour un utilisateur
 public void markAllAsRead(String numTelephone) {
     List<Notification> notifications = notificationRepository.findByNumTelephoneAndIsReadFalse(numTelephone);
     notifications.forEach(notification -> notification.setRead(true));
     notificationRepository.saveAll(notifications);
 }
 
 public void markIsRead(long ref) {
    Notification n=notificationRepository.findByRef(ref);
    n.setRead(true);
     notificationRepository.save(n);
 }
 
 
}
