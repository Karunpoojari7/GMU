//package com.gmu.leadservice.service.impl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//import org.springframework.stereotype.Service;
//
//import com.gmu.leadservice.entity.RefreshToken;
//import com.gmu.leadservice.entity.User;
//import com.gmu.leadservice.exception.UnauthorizedException;
//import com.gmu.leadservice.repository.RefreshTokenRepository;
//import com.gmu.leadservice.service.RefreshTokenService;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class RefreshTokenServiceImpl implements RefreshTokenService {
//
//    private final RefreshTokenRepository refreshTokenRepository;
//    private static final long REFRESH_TOKEN_DAYS = 30;
//
//    @Override
//    public RefreshToken createToken(User user) {
//        String token = UUID.randomUUID().toString();
//
//        RefreshToken refreshToken = RefreshToken.builder()
//                .token(token)
//                .user(user)
//                .expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_DAYS))
//                .revoked(false)
//                .expired(false)
//                .build();
//
//        return refreshTokenRepository.save(refreshToken);
//    }
//
//    @Override
//    @Transactional
//    public RefreshToken validateToken(String token) {
//        RefreshToken refreshToken = refreshTokenRepository.findByTokenWithUser(token)
//                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));
//
//        if (refreshToken.isRevoked()) {
//            throw new UnauthorizedException("Refresh token revoked");
//        }
//        if (refreshToken.isExpired()) {
//            throw new UnauthorizedException("Refresh token expired");
//        }
//        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
//            throw new UnauthorizedException("Refresh token expired");
//        }
//
//        return refreshToken;
//    }
//
//    @Override
//    public void revokeToken(String token) {
//        refreshTokenRepository.findByToken(token).ifPresent(refreshToken -> {
//            refreshToken.setRevoked(true);
//            refreshTokenRepository.save(refreshToken);
//        });
//    }
//
//    @Override
//    public void revokeAllTokens(Long userId) {
//        List<RefreshToken> tokens = refreshTokenRepository.findByUserSlNo(userId);
//        for (RefreshToken token : tokens) {
//            token.setRevoked(true);
//        }
//        refreshTokenRepository.saveAll(tokens);
//    }
//}



package com.gmu.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gmu.app.entity.RefreshToken;
import com.gmu.app.entity.User;
import com.gmu.app.exception.UnauthorizedException;
import com.gmu.app.repository.RefreshTokenRepository;
import com.gmu.app.service.RefreshTokenService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private static final long REFRESH_TOKEN_DAYS = 30;

    @Override
    public RefreshToken createToken(User user) {

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_DAYS))
                .revoked(false)
                .expired(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken validateToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByTokenWithUser(token)
                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));

        if (refreshToken.isRevoked()) {
            throw new UnauthorizedException("Refresh token revoked");
        }

        if (refreshToken.isExpired()) {
            throw new UnauthorizedException("Refresh token expired");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new UnauthorizedException("Refresh token expired");
        }

        return refreshToken;
    }

    @Override
    public void revokeToken(String token) {

        refreshTokenRepository.findByToken(token).ifPresent(refreshToken -> {

            refreshToken.setRevoked(true);

            refreshTokenRepository.save(refreshToken);

        });
    }

    @Override
    public void revokeAllTokens(Integer slNo) {

        List<RefreshToken> tokens = refreshTokenRepository.findByUserSlNo(slNo);

        for (RefreshToken token : tokens) {

            token.setRevoked(true);

        }

        refreshTokenRepository.saveAll(tokens);
    }

}