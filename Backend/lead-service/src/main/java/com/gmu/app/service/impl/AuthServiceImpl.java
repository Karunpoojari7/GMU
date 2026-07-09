package com.gmu.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gmu.app.config.CookieProperties;
import com.gmu.app.dto.request.LoginRequest;
import com.gmu.app.dto.request.RegisterRequest;
import com.gmu.app.dto.response.UserResponse;
import com.gmu.app.entity.RefreshToken;
import com.gmu.app.entity.User;
import com.gmu.app.exception.BadRequestException;
import com.gmu.app.exception.NotFoundException;
import com.gmu.app.exception.UnauthorizedException;
import com.gmu.app.mapper.UserMapper;
import com.gmu.app.repository.UserRepository;
import com.gmu.app.security.UserPrincipal;
import com.gmu.app.service.AuthService;
import com.gmu.app.service.RefreshTokenService;
import com.gmu.app.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieProperties cookieProperties;

    @Autowired
    private UserMapper userMapper;

    // ============================================================
    // LOGIN
    // ============================================================

    @Override
    public void login(LoginRequest request,
                      HttpServletResponse response) {

        if (request.getUsername() == null ||
                request.getUsername().trim().isEmpty()) {

            throw new BadRequestException("Username is required");
        }

        if (request.getPassword() == null ||
                request.getPassword().trim().isEmpty()) {

            throw new BadRequestException("Password is required");
        }

        Optional<User> optionalUser =
                userRepository.findByUsername(request.getUsername().trim());

        if (optionalUser.isEmpty()) {

            throw new UnauthorizedException(
                    "Invalid username or password");
        }

        User user = optionalUser.get();

        if (user.getStatus() == null ||
                !user.getStatus().trim()
                        .equalsIgnoreCase("ACTIVE")) {

            throw new UnauthorizedException(
                    "Your account is not active. Please contact administrator.");
        }

        boolean validPassword =
                isPasswordValid(
                        request.getPassword(),
                        user.getPassword());

        if (!validPassword) {

            throw new UnauthorizedException(
                    "Invalid username or password");
        }

        String accessToken =
                jwtUtil.generateAccessToken(user);

        RefreshToken refreshToken =
                refreshTokenService.createToken(user);

        ResponseCookie accessCookie =
                buildAccessCookie(accessToken);

        ResponseCookie refreshCookie =
                buildRefreshCookie(
                        refreshToken.getToken());

        response.addHeader(
                "Set-Cookie",
                accessCookie.toString());

        response.addHeader(
                "Set-Cookie",
                refreshCookie.toString());
    }

    // ============================================================
    // REFRESH TOKEN
    // ============================================================

    @Override
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) {

        String refreshTokenValue = getRefreshTokenFromCookie(request);

        if (refreshTokenValue == null) {

            throw new UnauthorizedException("Refresh token not found");
        }

        RefreshToken refreshToken =
                refreshTokenService.validateToken(refreshTokenValue);

        User user = refreshToken.getUser();

        String accessToken =
                jwtUtil.generateAccessToken(user);

        ResponseCookie accessCookie =
                buildAccessCookie(accessToken);

        response.addHeader(
                "Set-Cookie",
                accessCookie.toString());
    }

    // ============================================================
    // LOGOUT
    // ============================================================

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) {

        String refreshTokenValue =
                getRefreshTokenFromCookie(request);

        if (refreshTokenValue != null) {

            refreshTokenService.revokeToken(refreshTokenValue);
        }

        ResponseCookie accessCookie =
                ResponseCookie.from("accessToken", "")
                        .httpOnly(true)
                        .secure(cookieProperties.isSecure())
                        .path("/")
                        .maxAge(0)
                        .build();

        ResponseCookie refreshCookie =
                ResponseCookie.from("refreshToken", "")
                        .httpOnly(true)
                        .secure(cookieProperties.isSecure())
                        .path("/")
                        .maxAge(0)
                        .build();

        response.addHeader(
                "Set-Cookie",
                accessCookie.toString());

        response.addHeader(
                "Set-Cookie",
                refreshCookie.toString());
    }

    // ============================================================
    // GET CURRENT USER (/api/auth/me)
    // ============================================================

    @Override
    public UserResponse getUser() {

        UserPrincipal userPrincipal =
                (UserPrincipal) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        User user = userRepository.findByEmpId(userPrincipal.userId())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        if (user.getStatus() == null ||
                !user.getStatus().trim().equalsIgnoreCase("ACTIVE")) {

            throw new BadRequestException(
                    "Your account is currently inactive. Please contact your administrator.");
        }

        return userMapper.toResponse(user);
    }

    // ============================================================
    // TODO STEP 2 — admin_tools.php "Add User" business logic
    // ============================================================

    @Override
    public UserResponse createCounsellor(RegisterRequest request) {
        throw new UnsupportedOperationException("Pending Step 2");
    }

    @Override
    public UserResponse createLeadManager(RegisterRequest request) {
        throw new UnsupportedOperationException("Pending Step 2");
    }

    @Override
    public UserResponse createAdmin(RegisterRequest request) {
        throw new UnsupportedOperationException("Pending Step 2");
    }

    // ============================================================
    // COOKIE HELPERS
    // ============================================================

    private ResponseCookie buildAccessCookie(String token) {

        return ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(!cookieProperties.isSecure())
                .path("/")
                .maxAge(15 * 60)
                .sameSite("None")
                .build();
    }

    private ResponseCookie buildRefreshCookie(String token) {

        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(!cookieProperties.isSecure())
                .path("/")
                .maxAge(30L * 24 * 60 * 60)
                .sameSite("None")
                .build();
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {

        if (request.getCookies() == null) {
            return null;
        }

        for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {

            if ("refreshToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    // ============================================================
    // PASSWORD VALIDATION (same business logic as login.php)
    // ============================================================

    private boolean isPasswordValid(String inputPassword,
                                    String storedPassword) {

        if (storedPassword == null) {
            return false;
        }

        inputPassword = inputPassword.trim();
        storedPassword = storedPassword.trim();

        if (inputPassword.equals(storedPassword)) {
            return true;
        }

        if (storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$")) {

            org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder =
                    new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

            return encoder.matches(inputPassword, storedPassword);
        }

        if (storedPassword.length() == 32) {

            try {
                java.security.MessageDigest md =
                        java.security.MessageDigest.getInstance("MD5");

                byte[] hash = md.digest(inputPassword.getBytes());

                StringBuilder sb = new StringBuilder();

                for (byte b : hash) {
                    sb.append(String.format("%02x", b));
                }

                return sb.toString().equalsIgnoreCase(storedPassword);

            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}