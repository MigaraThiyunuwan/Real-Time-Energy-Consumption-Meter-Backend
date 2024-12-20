package com.iot.demo;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class EnergyWebSocketHandler extends TextWebSocketHandler {
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println(session.getId() + " established");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received Message: "+message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println(session.getId() + " closed");
    }

    public void sendEnergyData(String energyData){
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try{
                    session.sendMessage(new TextMessage(energyData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
