package com.gmu.app.service;

import com.gmu.app.entity.RefreshToken;
import com.gmu.app.entity.User;

public interface RefreshTokenService {

    RefreshToken createToken(User user);

    RefreshToken validateToken(String token);

    void revokeToken(String token);

    void revokeAllTokens(Integer slNo);

}