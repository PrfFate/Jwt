package com.aliacar.service;



import com.aliacar.jwt.AuthResponse;
import com.aliacar.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

    public AuthResponse refreshToken(RefreshTokenRequest request);
}
