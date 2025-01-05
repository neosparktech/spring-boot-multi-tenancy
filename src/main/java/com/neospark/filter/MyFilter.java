package com.neospark.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.neospark.config.TenantContext;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.info("Request URL: {} ", httpRequest.getRequestURI());
		String tenantId = httpRequest.getHeader("x-tenant-id");

		TenantContext.set(TenantContext.builder().tenantId(tenantId).build());
		chain.doFilter(request, response); // Continue the filter chain
		log.info("Tenant Cleared {}", TenantContext.get().getTenantId());
		TenantContext.clear();
	}
}
