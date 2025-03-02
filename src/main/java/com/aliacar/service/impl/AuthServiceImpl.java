package com.aliacar.service.impl;



import java.util.Optional;

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
import com.aliacar.model.User;
import com.aliacar.repository.UserRepository;
import com.aliacar.service.IAuthService;

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

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);
            Optional<User> optionalUser= userRepository.findByUsername(request.getUsername());
            String token= jwtService.generateToken(optionalUser.get());
            return new AuthResponse(token);
    
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
