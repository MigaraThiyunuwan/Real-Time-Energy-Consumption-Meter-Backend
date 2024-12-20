package com.iot.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final EnergyWebSocketHandler energyWebSocketHandler;

    public WebSocketConfig(EnergyWebSocketHandler energyWebSocketHandler) {
        this.energyWebSocketHandler = energyWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(energyWebSocketHandler, "/energy").setAllowedOrigins("*");
    }
}
