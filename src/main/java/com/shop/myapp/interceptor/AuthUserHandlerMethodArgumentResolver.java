package com.shop.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.shop.myapp.dto.Member;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 2. @AuthUser가 붙어 있는지 확인
				AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
				
				// 3. @AuthUser가 안붙어 있는 경우
				if( authUser == null ) {
					return false;
				}
				
				// 4. UserVO 타입이 아닌 경우
				if( parameter.getParameterType().equals(Member.class) == false) {
					return false;
				}
				
				return true;
	}

	@Override
	public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 1. 파라미터에 @AuthUser가 붙어 있는지 , 타입이 UserVO인지 확인 
				if( supportsParameter(param) == false ) {
					// 내가 해석할 수 있는 파라미터가 아니다.
					return WebArgumentResolver.UNRESOLVED;
				}
				
				// 5. 여기까지 진행이 되었다면, @AuthUser가 붙어있고 타입이 UserVO인 경우이다.
				HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
				HttpSession session = request.getSession();
				if( session == null) {
					return null;
				}
				
				return session.getAttribute("authUser");
	}

}
