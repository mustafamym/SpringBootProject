package com.spring.demo.jwt.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CorsFilter implements Filter {

	@Value(value = "${app.security.cors.headers:Authorization, Content-Type, Content-Disposition, Accept, x-requested-with, Cache-Control}")
	private String corsHeaders;

	@Value(value = "${app.security.cors.methods:POST, GET, OPTIONS, DELETE, PUT}")
	private String corsMethods;

	@Value(value = "${app.security.cors.origin:*}")
	private String corsOrigin;

	@Value(value = "${app.security.cors.max-age:3600}")
	private String corsMaxAge;

	@Value(value = "${app.headers.cache-control:no-store, max-age=0, must-revalidate}")
	private String headersCacheControl;

	@Value(value = "${app.headers.pragma:no-cache}")
	private String headersPragma;
	
	@Value(value = "${app.headers.allow-credentials:true}")
	private String headersAllowCredentials;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-Control-Allow-Origin",
				corsOrigin.equalsIgnoreCase("*") && StringUtils.hasText(httpRequest.getHeader("Origin"))
						? httpRequest.getHeader("Origin") : corsOrigin);
		res.setHeader("Access-Control-Allow-Methods", corsMethods);
		res.setHeader("Access-Control-Max-Age", corsMaxAge);
		// res.setHeader("X-Frame-Options", "SAMEORIGIN");
		res.setHeader("Access-Control-Allow-Headers", corsHeaders);
		res.setHeader("Access-Control-Expose-Headers", corsHeaders);
		res.setHeader("Cache-Control", headersCacheControl);
		res.setHeader("Pragma", headersPragma);

		if ("true".equalsIgnoreCase(headersAllowCredentials)) {
			res.setHeader("Access-Control-Allow-Credentials", headersAllowCredentials);
		}
		
		chain.doFilter(request, res);
	}

	@Override
	public void destroy() {
	}
}