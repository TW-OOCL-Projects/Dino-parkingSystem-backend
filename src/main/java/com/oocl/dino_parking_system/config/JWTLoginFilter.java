package com.oocl.dino_parking_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dino_parking_system.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
