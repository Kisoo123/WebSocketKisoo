package com.ks.websocket.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ks.websocket.member.model.dto.Member;
import com.ks.websocket.model.dto.Chat;

@Repository
public class ChatDao {
	public Member selectMemberById(SqlSessionTemplate session,String memberId) {
		return session.selectOne("member.selectMemberById",memberId);
	}
	public int selectChatSeq(SqlSessionTemplate session) {
		return session.selectOne("chat.selectChatSeq");
	}
	
	public int insertChatStatus(SqlSessionTemplate session,Map<String, Object> chatInfo) {
		return session.insert("chat.insertChatStatus",chatInfo);
	}
	
	public int insertChat(SqlSessionTemplate session,Map<String, Object> chatInfo) {
		return session.insert("chat.insertChat",chatInfo);
	}
	
	public List<Chat>selectRoomChatList(SqlSessionTemplate session,int roomNo){
		return session.selectList("chat.selectRoomChatList",roomNo);
	}
	
	public List<Member>selectAllMembers(SqlSessionTemplate session){
		return session.selectList("member.selectAllMembers");
	}
	public Integer selectRoomId(SqlSessionTemplate session,Map<String,Object>param) {
		Integer result = session.selectOne("member.selectRoomId", param);
		return result;
	}	
	public int insertRoomId(SqlSessionTemplate session, List<String> memberIds) {
        int result = 0;
        int roomId = session.selectOne("member.getSequenceValue");

        for (int i = 0; i < memberIds.size(); i++) {
            Map<String, Object> params = new HashMap<>();
            System.out.println(memberIds.get(i));
            params.put("roomId", roomId);
            params.put("memberId", memberIds.get(i));
            result += session.insert("member.insertSingleRoomId", params);
        }
        System.out.println("==================");
        System.out.println(roomId);
        System.out.println("==================chatDao");
        return roomId;
    }
	public List<Member> selectRoomList(SqlSessionTemplate session, int roomId){
		return session.selectList("chat.selectRoomList",roomId);
	}
	public List<Integer> selectMyRoomId(SqlSessionTemplate session, String memberId){
		return session.selectList("chat.selectMyRoomId",memberId);
	}

}
