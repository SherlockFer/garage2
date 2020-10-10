package com.apress.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.filter.JwtFilter;
import com.apress.jwt.JwtUtil;

@Configuration
public class JwtFilterConfig {

	@Autowired
	private JwtUtil jwtUtil;

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
		registrationBean.setFilter(new JwtFilter(jwtUtil));
		registrationBean.addUrlPatterns("/bookings/*", "/services/*", "/parts/*", "/users/*");
		return registrationBean;
	}

}
