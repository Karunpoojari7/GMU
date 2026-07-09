package com.gmu.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmu.app.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Query("""
            SELECT rt
            FROM RefreshToken rt
            JOIN FETCH rt.user
            WHERE rt.token = :token
            """)
    Optional<RefreshToken> findByTokenWithUser(String token);

    void deleteByUserSlNo(Integer slNo);

    List<RefreshToken> findByUserSlNo(Integer slNo);

}