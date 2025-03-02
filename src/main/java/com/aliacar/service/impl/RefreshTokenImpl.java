package com.aliacar.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliacar.jwt.AuthResponse;
import com.aliacar.jwt.JwtService;
import com.aliacar.jwt.RefreshTokenRequest;
import com.aliacar.model.RefreshToken;
import com.aliacar.model.User;
import com.aliacar.repository.RefreshTokenRepository;
import com.aliacar.service.IRefreshTokenService;

@Service
public class RefreshTokenImpl implements IRefreshTokenService{

    public boolean isRefreshTokenExpired(Date expireDate){
        return new Date().before(expireDate);
    }

    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional= refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if(optional.isEmpty()){
            System.out.println("REFRESH TOKEN GECERSIZ"+request.getRefreshToken());
        }
        RefreshToken refreshToken=optional.get();
        if(!isRefreshTokenExpired(refreshToken.getExpireDate())){
            System.out.println("REFRESH TOKEN EXPİRE OLMUSTUR"+request.getRefreshToken());
        }
        String accesToken= jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken=refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

        
        return new AuthResponse(accesToken,savedRefreshToken.getRefreshToken());
    }

    
}
