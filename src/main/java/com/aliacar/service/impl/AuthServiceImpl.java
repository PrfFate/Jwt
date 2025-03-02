package com.aliacar.service.impl;



import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;
import com.aliacar.jwt.AuthResponse;
import com.aliacar.jwt.JwtService;
import com.aliacar.model.RefreshToken;
import com.aliacar.model.User;
import com.aliacar.repository.RefreshTokenRepository;
import com.aliacar.repository.UserRepository;
import com.aliacar.service.IAuthService;
import com.aliacar.service.IRefreshTokenService;

@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setUser(user);

        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);
            Optional<User> optionalUser= userRepository.findByUsername(request.getUsername());
            String accessToken= jwtService.generateToken(optionalUser.get());

            RefreshToken refreshToken =createRefreshToken(optionalUser.get());
            refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());
            
        } catch (Exception e) {
            System.out.println("Kullanici adi veya sifre hatali");
        }
        return null;
    }

    @Override
    public DtoUser register(AuthRequest request) {
        DtoUser dto=new DtoUser();
        User user =new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser=userRepository.save(user);

        BeanUtils.copyProperties(savedUser,dto);
    
        


        return dto;
    }

    

    

   
    

   

    

    
}
