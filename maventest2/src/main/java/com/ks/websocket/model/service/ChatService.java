package com.ks.websocket.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.ks.websocket.member.model.dto.Member;
import com.ks.websocket.model.dao.ChatDao;
import com.ks.websocket.model.dto.Chat;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class ChatService {
//	private final HttpSession session1;
	private final SqlSessionTemplate session;
	private final ChatDao dao;
	public int insertChat(Chat chat) {
		int chatSeq = dao.selectChatSeq(session);
//		Member m  = (Member)session1.getAttribute("loginMember");

		Map<String, Object> chatInfo = new HashMap<>();
		chatInfo.put("seq", chatSeq);
		chatInfo.put("chat", chat);
//		chatInfo.put("loginMember",	m.getMemberNo());
//		dao.insertChatStatus(session,chatInfo);
		return dao.insertChat(session,chatInfo);
	}
	public List<Chat> selectRoomChatList(int roomNo){
		return dao.selectRoomChatList(session,roomNo);
	}
	public List<Member> selectAllMembers(){
		return dao.selectAllMembers(session);
	};
	public int selectRoomId(Map<String,Object> param) {
		Integer roomId = dao.selectRoomId(session,param);
		System.out.println(param.get("memberIds")+"dadasddsadsasad");
		List<String> memberIds=(List<String>) param.get("memberIds");
		if(roomId==null)
			{dao.insertRoomId(session,memberIds);
			roomId = dao.selectRoomId(session,param);
			System.out.println(roomId+"charserviceRommIDdkasnkdlasndlaskdnaslkdlkjnsadlnk");
			}
		return roomId;
	}
	
	public Map<Integer,List<Member>> selectMyRoomList(String memberId){
		Map<Integer, List<Member>>roomMemberInfo = new HashMap<>();
		List<Integer> myRoomId = dao.selectMyRoomId(session,memberId);
		for(int i:myRoomId) {
			roomMemberInfo.put(i, dao.selectRoomList(session,i));
		}
		return roomMemberInfo;
	}
	
	public Member selectMemberById(String memberId) {
		return dao.selectMemberById(session,memberId);
	}
}
