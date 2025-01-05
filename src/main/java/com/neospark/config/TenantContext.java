package com.neospark.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TenantContext {
	private static final ThreadLocal<TenantContext> context = new ThreadLocal<>();
	private String tenantId;

	public static TenantContext get() {
		return context.get();
	}

	public static void set(TenantContext context) {
		TenantContext.context.set(context);
	}

	public static void clear() {
		TenantContext.context.remove();
	}
}
