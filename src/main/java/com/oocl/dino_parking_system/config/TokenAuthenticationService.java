package com.oocl.dino_parking_system.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Created by Vito Zhuang on 7/31/2018.
 */
class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 1000*60*60*24*1; // 1 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorizationz";

	static void addAuthentication(HttpServletResponse res, String username) {
		System.out.println("===addAuth");
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		System.out.println("===addAuth:token:"+JWT);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		System.out.println(">>>>getAuth-token: "+token);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			System.out.println(">>>>>getAuth:"+user);
			return user != null ?
					new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
					null;
		}
		return null;
	}
}