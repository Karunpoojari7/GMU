package com.gmu.gateway.constant;

public final class HeaderConstant {

    private HeaderConstant() {
        // Restrict instantiation
    }

    public static final String CORRELATION_ID = "X-Correlation-ID";
    public static final String USER_ID = "X-User-Id";
    public static final String USER_ROLE = "X-User-Role";
    public static final String USER_EMAIL = "X-User-Email";
    public static final String SERVICE_NAME = "X-Service-Name";
    public static final String INTERNAL_TOKEN = "X-Internal-Token";
}