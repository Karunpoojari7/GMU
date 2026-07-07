package com.gmu.gateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import com.gmu.gateway.config.SecurityProperties;

@Component
@RequiredArgsConstructor
public class RouteValidator {

    private final SecurityProperties securityProperties;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public boolean isPublic(String path) {
        return securityProperties.getPublicEndpoints()
                .stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }
}