package com.oocl.dino_parking_system.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.service.ParkingBoyService;
import com.oocl.dino_parking_system.util.PasswordEncoder;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.CollationKey;

import static com.oocl.dino_parking_system.constant.Constants.SALT_STRING;

/**
 * Created by Vito Zhuang on 7/31/2018.
 */

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final UserDetailsService userDetailsService;

	private final ParkingBoyService parkingBoyService;

	public JWTLoginFilter(String url, AuthenticationManager authManager, UserDetailsService userDetailsService, ParkingBoyService parkingBoyService) {
		super(new AntPathRequestMatcher(url));
		this.userDetailsService = userDetailsService;
		this.parkingBoyService = parkingBoyService;
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
						new PasswordEncoder(SALT_STRING, "MD5")
								.encode(creds.getPassword())
								.substring(0, 7),
						userDetails.getAuthorities()
				)
		);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(res, auth.getName(), auth.getAuthorities());
		JSONObject json = parkingBoyService.findUnReadOrderNum(auth.getName());
		String roleName = auth.getAuthorities().toString().replace("[","").replace("]","");
		Cookie cookie1 = new Cookie("role",roleName);
		Cookie cookie2 = new Cookie("id", json.get("parkingBoyId").toString());
		cookie1.setPath("/");
		cookie1.setMaxAge(3600);
		cookie2.setPath("/");
		cookie2.setMaxAge(3600);
		res.addCookie(cookie1);
		res.addCookie(cookie2);
		WebSocketServer.sendInfo(json.toJSONString(), json.get("parkingBoyId").toString());
	}
}
