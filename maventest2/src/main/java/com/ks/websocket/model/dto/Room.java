package com.ks.websocket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    
    private Long id;
    private String name;


    /**
     * 채팅방 생성
     * @param name 방 이름
     * @return Room Entity
     */

    public static Room createRoom(String name) {
        return Room.builder()
                .name(name)
                .build();
    }
}