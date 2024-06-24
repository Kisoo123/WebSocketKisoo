package com.ks.websocket.model.dto;

import java.sql.Date;

import com.ks.websocket.member.model.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    private int roomId;
    private int memberNo;
    private String message;
    private Date sendDate;
    private int chatId;

    public static Chat createChat(int roomId, int memberNo, String message, Date sendDate, int chatId) {
        return Chat.builder()
        		.roomId(roomId)
                .memberNo(memberNo)
                .message(message)
                .sendDate(sendDate)
                .chatId(chatId)
                .build();
    }
}
