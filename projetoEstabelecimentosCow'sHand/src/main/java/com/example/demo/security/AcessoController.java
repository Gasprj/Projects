package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AcessoController extends HandlerInterceptorAdapter{
	
	@SuppressWarnings("static-access")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("usuarioLogado") == null) {
			request.getRequestDispatcher("/redirect").forward(request, response);
			return false;
		}else
			return true;
	}
	
	public void postHandler(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		super.postHandle(request, response, handler, mv);
	}

}
