package com.ks.websocket.controller;

import static com.ks.websocket.model.dto.Chat.createChat;

import javax.servlet.http.HttpSession;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.ks.websocket.model.dto.Chat;
import com.ks.websocket.model.service.ChatService;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatService service;
	@MessageMapping("/{roomId}") //여기로 전송되면 메서드 호출 -> WebSocketConfig prefixes 에서 적용한건 앞에 생략
	@SendTo("/room/{roomId}")   //구독하고 있는 장소로 메시지 전송 (목적지)  -> WebSocketConfig Broker 에서 적용한건 앞에 붙어줘야됨
	public Chat test(@DestinationVariable int roomId, Chat message) {
//		HttpSession session=(HttpSession)RequestContextHolder.currentRequestAttributes()
//		.resolveReference(RequestAttributes.REFERENCE_SESSION);
//		System.out.println(session);
		
		System.out.println("Received message: " + message);
        // 채팅 저장
        Chat chat = createChat(roomId, message.getMemberNo(), message.getMessage(), message.getSendDate(),message.getChatId());
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
