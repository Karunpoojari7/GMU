package com.gmu.gateway.security;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

// Import our new constants
import com.gmu.gateway.constant.HeaderConstant;
import com.gmu.gateway.constant.JwtConstant;
import com.gmu.gateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtGatewayFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final RouteValidator routeValidator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        
        // Generate Correlation ID for Request Tracing
        String correlationId = UUID.randomUUID().toString();
        exchange.getResponse().getHeaders().add(HeaderConstant.CORRELATION_ID, correlationId);

        /**
         * Public endpoints
         */
        if (routeValidator.isPublic(path)) {
            log.info("PUBLIC PATH: {} | CorrelationId: {}", path, correlationId);
            
            String internalToken = jwtUtil.generateInternalToken(
                    Map.of(
                            JwtConstant.CLAIM_SERVICE, JwtConstant.GATEWAY_SERVICE_NAME
                    )
            );

            
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(HeaderConstant.CORRELATION_ID, correlationId)
                    .header(HeaderConstant.INTERNAL_TOKEN, internalToken)
                    .build();
                    
            return chain.filter(exchange.mutate().request(request).build());
        }
        
        log.info("SECURE PATH: {} | CorrelationId: {}", path, correlationId);

        /**
         * Read access token cookie
         */
        HttpCookie cookie = exchange.getRequest().getCookies().getFirst(JwtConstant.ACCESS_TOKEN_COOKIE);

        if (cookie == null) {
            return sendErrorResponse(exchange, "Missing access_token cookie", correlationId);
        }

        String token = cookie.getValue();

        try {
            Claims claims = jwtUtil.validateToken(token);

            // Extract claims using JwtConstants
            String userId = String.valueOf(claims.get(JwtConstant.CLAIM_USER_ID));
            String role = claims.get(JwtConstant.CLAIM_ROLE).toString();
            String email = claims.getSubject();

            /**
             * Generate Internal token
             */
            String internalToken = jwtUtil.generateInternalToken(
                    Map.of(
                            JwtConstant.CLAIM_SERVICE, JwtConstant.GATEWAY_SERVICE_NAME,
                            JwtConstant.CLAIM_USER_ID, userId,
                            JwtConstant.CLAIM_ROLE, role,
                            JwtConstant.CLAIM_EMAIL, email
                    )
            );

            /**
             * Forward headers using HeaderConstants
             */
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(HeaderConstant.USER_ID, userId)
                    .header(HeaderConstant.USER_ROLE, role)
                    .header(HeaderConstant.USER_EMAIL, email)
                    .header(HeaderConstant.SERVICE_NAME, JwtConstant.GATEWAY_SERVICE_NAME)
                    .header(HeaderConstant.INTERNAL_TOKEN, internalToken)
                    .header(HeaderConstant.CORRELATION_ID, correlationId)
                    .build();

            return chain.filter(
                    exchange.mutate()
                            .request(request)
                            .build()
            );

        } catch (Exception ex) {
            log.error("Token validation failed: {} | CorrelationId: {}", ex.getMessage(), correlationId);
            return sendErrorResponse(exchange, "Invalid or expired token", correlationId);
        }
    }

    /**
     * Helper to return clean JSON Error Responses
     */
    private Mono<Void> sendErrorResponse(ServerWebExchange exchange, String message, String correlationId) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = String.format(
                "{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"%s\", \"path\": \"%s\", \"correlationId\": \"%s\"}",
                message, exchange.getRequest().getURI().getPath(), correlationId
        );

        byte[] bytes = jsonPayload.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}