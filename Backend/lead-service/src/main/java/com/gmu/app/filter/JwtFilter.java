package com.gmu.app.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gmu.app.config.SecurityProperties;
import com.gmu.app.security.UserPrincipal;
import com.gmu.app.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final List<String> PUBLIC_URLS = List.of(
    		"/api/leads/auth/login",
            "/api/leads/auth/refresh",
            "/api/leads/auth/logout"
    );

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String path = request.getServletPath();

        return PUBLIC_URLS.stream().anyMatch(path::equals);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api/auth/public")) {
            filterChain.doFilter(request, response);
            return;
        }

        String internalToken = request.getHeader("X-Internal-Token");

        if (internalToken == null) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Internal token missing");

            return;
        }
        

        try {

            Claims claims = jwtUtil.validateToken(internalToken);

            String service = claims.get("service", String.class);

            if (!securityProperties.getAllowedServices().contains(service)) {

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Service not allowed");

                return;
            }

            String empId = claims.get("empId", String.class);

            String userGroup = claims.get("userGroup", String.class);

            UserPrincipal principal = new UserPrincipal(empId, userGroup);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + userGroup))
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception ex) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");

            return;
        }

        filterChain.doFilter(request, response);
    }

}