package com.nts.reservation.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

/**
 * 로그인 인터셉터
 * 
 * @author : donggun.chung
 * @version : 1.0 최신
 * @since : 2019. 8. 13.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @return true / false
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie = WebUtils.getCookie(request, "reservationEmail");

		if (cookie != null) {
			response.sendRedirect("myreservation");
			return false;
		}

		return true;
	}

	/**
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

}