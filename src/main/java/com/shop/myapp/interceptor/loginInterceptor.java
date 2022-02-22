package com.shop.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.shop.myapp.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class loginInterceptor implements HandlerInterceptor{
	
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			log.info(this.getClass().toString());
			Member member = (Member)request.getSession().getAttribute("member");
			String loginId = member.getMemberId();
			
			if(loginId != null) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath() + "/members/login");
				String msg = "로그인을 해주세요";
				request.getSession().setAttribute("msg", msg);
				return false;
			}
		}
}
