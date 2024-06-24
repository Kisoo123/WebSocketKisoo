package com.ks.websocket.member.model.dto;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
		private String memberId;           // 회원 ID (int)
	    private String memberNickname;  // 회원 닉네임 (String)
	    private String memberGrade;        // 회원 등급 (int)
	    private String memberPassword;  // 회원 비밀번호 (String)
	    private String memberName;      // 회원 이름 (String)
	    private String memberPhone;     // 회원 전화번호 (String)
	    private String memberEmail;     // 회원 이메일 (String)
	    private String memberAddress;   // 회원 주소 (String)
	    private String memberIdNumber;  // 회원 신분증 번호 (String)
	    private int memberPoint;        // 회원 포인트 (int)
	    private Date memberEnrollDate; // 회원 가입 일자 (LocalDate)
	    private int memberNo;           // 회원 번호 (int)
	    private Long id;

	
}
