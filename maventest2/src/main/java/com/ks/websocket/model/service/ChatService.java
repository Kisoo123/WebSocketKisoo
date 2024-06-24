package com.ks.websocket.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ks.websocket.member.model.dto.Member;
import com.ks.websocket.model.dao.ChatDao;
import com.ks.websocket.model.dto.Chat;
import com.ks.websocket.model.dto.ChatRoom;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ChatService {
//	private final HttpSession session1;
	private final SqlSessionTemplate session;
	private final ChatDao dao;
	public int insertChat(Chat chat) {
		int chatSeq = dao.selectChatSeq(session);
		
		Map<String, Object> chatInfo = new HashMap<>();
		chatInfo.put("seq", chatSeq);
		chatInfo.put("chat", chat);
		chatInfo.put("memberNo", chat.getMemberNo());
		
		int result = dao.insertChat(session,chatInfo);
		List<ChatRoom> myRoomMembers = dao.selectMyRoomMembers(session,chat.getRoomId());
		for(ChatRoom m : myRoomMembers) {
			    System.out.println(m.getMemberNo());
	
		}
		dao.insertChatStatus(session,chatInfo);
		return result;
	}
	public List<Chat> selectRoomChatList(int roomNo){
		return dao.selectRoomChatList(session,roomNo);
	}

	public List<Member> selectAllMembers(){
		return dao.selectAllMembers(session);
	};
	public int selectRoomId(Map<String,Object> param) {
		Integer roomId = dao.selectRoomId(session,param);
		System.out.println(param.get("memberNos")+"dadasddsadsasad");
		List<Integer> memberNos=(List<Integer>) param.get("memberNos");
		if(roomId==null){
			dao.insertRoomId(session,memberNos);
			roomId = dao.selectRoomId(session,param);
			System.out.println(roomId+"charserviceRommIDdkasnkdlasndlaskdnaslkdlkjnsadlnk");
			}
		return roomId;
	}
	
	public Map<Integer,List<Member>> selectMyRoomList(int memberNo){
		Map<Integer, List<Member>>roomMemberInfo = new HashMap<>();
		List<Integer> myRoomId = dao.selectMyRoomId(session,memberNo);
		for(int i:myRoomId) {
			roomMemberInfo.put(i, dao.selectRoomList(session,i));
		}
		return roomMemberInfo;
	}
	
	public Member selectMemberById(String memberId) {
		return dao.selectMemberById(session,memberId);
	}
}
