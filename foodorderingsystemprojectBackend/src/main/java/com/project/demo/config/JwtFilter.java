package com.project.demo.config;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
	
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		
		final String authHeader = request.getHeader("Authorization");
		
		// Allow access to the login endpoint
		if (request.getRequestURI().equals("/api/auth/login")) {
			chain.doFilter(req, res);
			return;
		}
		
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
			return;
		}
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
			return;
		}
		
		final String token = authHeader.substring(7);
		
		try {
			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
		} catch (final SignatureException e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
			return;
		}
		
		chain.doFilter(request, response);
	}
}





//public class JwtFilter extends GenericFilterBean {
//	
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//	
//		final HttpServletRequest request = (HttpServletRequest) req;
//		final HttpServletResponse response = (HttpServletResponse) res;
//		
//		final String authHeader = request.getHeader("authorization");
//		
//		// Allow access to the login endpoint
//		if (request.getRequestURI().equals("/api/auth/login")) {
//			chain.doFilter(req, res);
//			return;
//		}
//		
//		if ("OPTIONS".equals(request.getMethod())) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			throw new ServletException("Missing or invalid Authorization header");
//		}
//		
//		final String token = authHeader.substring(7);
//		
//		try {
//			final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
//			request.setAttribute("claims", claims);
//		} catch (final SignatureException e) {
//			throw new ServletException("Invalid token");
//		}
//		
//		chain.doFilter(request, response);
//	}
//}

