package com.gmu.gateway.constant;

public final class JwtConstant {

    private JwtConstant() {
        // Restrict instantiation
    }

    // Cookie Identifiers
    public static final String ACCESS_TOKEN_COOKIE = "access_token";

    // JWT Claim Keys
    public static final String CLAIM_USER_ID = "userId";
    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_EMAIL = "email";
    public static final String CLAIM_SERVICE = "service";

    // System Defaults
    public static final String GATEWAY_SERVICE_NAME = "gateway";
}