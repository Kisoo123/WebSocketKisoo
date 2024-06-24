package com.ks.websocket.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ks.websocket.member.model.dto.Member;
import com.ks.websocket.model.dto.Chat;
import com.ks.websocket.model.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ChatService service;
	
	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public String chat(
			Model model,
			@RequestParam(required = false) List<String> memberNo,
			HttpSession session,
			@RequestParam(required = false) Integer roomId
			) {
		if(memberNo!=null) {
			model.addAttribute("roomId",roomId);
		}
		
		if(roomId==null) {
			Map<String, Object> param = new HashMap<>();
			param.put("memberNos", memberNo);
			param.put("memberSize", memberNo.size());
			roomId = service.selectRoomId(param);
		}
		List<Chat> chats = service.selectRoomChatList(roomId);
		Gson gson = new Gson();
		model.addAttribute("chatList",gson.toJson(chats));
		model.addAttribute("roomId",roomId);
		return "chat";
	}
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	@RequestMapping(value = "postMessage", method=RequestMethod.GET)
	public String postMessage() {
		return "postMessage";
	}
	@RequestMapping(value = "chatRoom", method=RequestMethod.GET)
	public String chatRoom(Model model,HttpSession session,@RequestParam String inputValue) {
		
		Member member = service.selectMemberById(inputValue);
        session.setAttribute("loginMember", member); // 여따가 필드 더 필요하면 추가
		Map<Integer,List<Member>>roomList = service.selectMyRoomList(member.getMemberNo());
//		model.addAttribute("roomId",roomId);
		model.addAttribute("roomList",roomList);
		List<Member>members = service.selectAllMembers();	
		model.addAttribute("members",members);
		
		return "chatRoom";
	}
	@RequestMapping(value = "loadRecentChat", method = RequestMethod.POST)
	@ResponseBody
	public List<Chat> loadChat(@RequestParam int roomId) {
	    List<Chat> chats = service.selectRoomChatList(roomId);
	    return chats;
	}


	
}