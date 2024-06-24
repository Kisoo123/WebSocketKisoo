package com.ks.websocket.controller;

import static com.ks.websocket.model.dto.Chat.createChat;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.expression.ParseException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ks.websocket.model.dto.Chat;
import com.ks.websocket.model.service.ChatService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatService service;
	@MessageMapping("/{roomId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
	@SendTo("/room/{roomId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
	public Chat test(@DestinationVariable Long roomId, Chat message) {
        System.out.println("Received message: " + message);
        // 채팅 저장
        Chat chat = createChat(roomId, message.getSender(), message.getMessage(), message.getSendDate());
        System.out.println(message.getSendDate());
        try {
            int result = service.insertChat(chat);
            System.out.println("Insert result: " + result);
        } catch (Exception e) {
            System.err.println("Exception occurred while inserting chat: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("After insertChat call");

        return chat;
    
	}
	
}
