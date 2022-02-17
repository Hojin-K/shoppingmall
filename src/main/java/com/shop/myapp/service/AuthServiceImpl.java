package com.shop.myapp.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.shop.myapp.dto.Member;
@Service
public class AuthServiceImpl implements AuthService {
	private final HttpSession session;
	
	public AuthServiceImpl(HttpSession session) {
		this.session = session;
	}

	@Override
	public boolean checkMemberId(String memberId) {
		Member member = (Member)session.getAttribute("member");

		return member.getMemberId().equals(memberId);
	}

}
