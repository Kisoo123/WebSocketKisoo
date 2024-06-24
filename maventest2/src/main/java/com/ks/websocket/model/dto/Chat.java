package com.ks.websocket.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    private Long roomId;
    private String sender;
    private String message;
    private Date sendDate;

    public static Chat createChat(Long roomId, String sender, String message, Date sendDate) {
        return Chat.builder()
        		.roomId(roomId)
                .sender(sender)
                .message(message)
                .sendDate(sendDate)
                .build();
    }
}
