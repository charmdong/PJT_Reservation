package com.nts.reservation.interceptor;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String clientIP = getUserIP(request);
		String requestUrl = request.getRequestURL().toString();
		String requestTime = LocalDateTime.now().toString();

		logger.debug("[ {} | {} | {} ]", requestTime, clientIP, requestUrl);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public String getUserIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

}
