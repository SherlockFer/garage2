package com.apress.filter;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.apress.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class JwtFilter extends GenericFilterBean {

	private static final Logger Log = LoggerFactory.getLogger(JwtFilter.class);

	private JwtUtil jwtUtil;

	public JwtFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		if ("OPTIONS".equals(request.getMethod())) {
			chain.doFilter(request, response);
		} else {

			try {
				String authHeader = request.getHeader("Authorization");
				log.info("validating token: {}", authHeader);
				if (StringUtils.startsWith(authHeader, "token ")) {
					String token = authHeader.replace("token", "");
					Claims claims = jwtUtil.decode(token);
					log.info("Token details: {}", claims);
					String role = (String) claims.get("role");
					Long userId = Long.valueOf(claims.get("user_id").toString());
					Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null,
							Arrays.asList(new SimpleGrantedAuthority(role)));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					log.info("token not found in HTTP headers");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					return;
				}
				chain.doFilter(request, response);
			} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
				log.info("Unable to decode token found in HTTP header");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

		}
	}
}
