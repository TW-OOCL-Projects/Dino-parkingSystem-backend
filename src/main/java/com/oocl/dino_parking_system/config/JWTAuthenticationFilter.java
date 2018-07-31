package com.oocl.dino_parking_system.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Vito Zhuang on 7/31/2018.
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request,
	                     ServletResponse response,
	                     FilterChain filterChain)
			throws IOException, ServletException {

		Authentication authentication = TokenAuthenticationService
				.getAuthentication((HttpServletRequest)request);
		System.out.println("===doFilter:authentication");
		SecurityContextHolder.getContext()
				.setAuthentication(authentication);
		filterChain.doFilter(request,response);
	}
}