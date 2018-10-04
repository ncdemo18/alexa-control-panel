package com.netcracker.alexa.controlpanel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint to which a WebSocket client will need to connect to for the WebSocket handshake.
        registry.addEndpoint("/alexa").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // prefix for subscribe and broadcasting requests
        registry.enableSimpleBroker("/topic");

        // requests begins with "/app" are routed to @MessageMapping methods in @Controller classes.
        registry.setApplicationDestinationPrefixes("/app");
    }
}
