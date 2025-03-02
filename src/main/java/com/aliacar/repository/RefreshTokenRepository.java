package com.aliacar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.aliacar.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>{

    //@Query(value = "from RefreshToken r where r.refreshToken =:refreshToken")
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
