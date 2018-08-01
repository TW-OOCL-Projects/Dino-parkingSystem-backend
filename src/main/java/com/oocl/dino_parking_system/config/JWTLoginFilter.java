package com.oocl.dino_parking_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Vito Zhuang on 7/31/2018.
 */

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final UserDetailsService userDetailsService;

	public JWTLoginFilter(String url, AuthenticationManager authManager, UserDetailsService userDetailsService) {
		super(new AntPathRequestMatcher(url));
		this.userDetailsService = userDetailsService;
		setAuthenticationManager(authManager);

	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		User creds = new ObjectMapper()
				.readValue(req.getInputStream(), User.class);
		UserDetails userDetails = userDetailsService.loadUserByUsername(creds.getUsername());
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						creds.getUsername(),
						creds.getPassword(),
						userDetails.getAuthorities()
				)
		);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(res, auth.getName(),auth.getAuthorities());
	}
}
