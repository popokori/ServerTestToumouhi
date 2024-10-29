package mr.gov.masef.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Envoyer une notification à un utilisateur spécifique
    public void sendNotificationToUser(String numTelephone, String message,String url,long ref,String formattedDate,String formattedTime ) {
        // Créer un objet JSON pour le message
    	
    	
    	
        String jsonMessage = "{\"message\":\"" + message + "\",\"url\":\"" + url + "\",\"ref\":\"" + ref + "\",\"date\":\"" + formattedDate + "\",\"heur\":\"" + formattedTime + "\"}";
        
        // Envoyer le message au format JSON
        messagingTemplate.convertAndSend("/topic/notifications/" + numTelephone, jsonMessage);
    }
}