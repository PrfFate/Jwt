package com.aliacar.service.impl;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;
import com.aliacar.model.User;
import com.aliacar.repository.UserRepository;
import com.aliacar.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

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
