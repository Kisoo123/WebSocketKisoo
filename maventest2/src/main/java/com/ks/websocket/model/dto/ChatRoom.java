package com.ks.websocket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    
    private int roomId;
    private int memberNo;
    

    public static ChatRoom createRoom(int roomId,int memberNo) {
        return ChatRoom.builder()
                .roomId(roomId)
                .memberNo(memberNo)
                .build();
    }
}