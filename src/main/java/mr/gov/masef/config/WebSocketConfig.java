package mr.gov.masef.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
	    // Autoriser toutes les origines sur localhost, quel que soit le port
		registry.addEndpoint("api/auth/ws")
        .setAllowedOriginPatterns(
            "*"// Adresse IP locale de votre machine
        )
        .withSockJS();
	}



    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Préfixe pour les messages envoyés depuis l'application
        registry.setApplicationDestinationPrefixes("/app");
        // Activer un simple broker pour diffuser des messages aux clients
        registry.enableSimpleBroker("/topic");
    }
}