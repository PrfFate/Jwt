package com.aliacar.controller;


import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;
import com.aliacar.jwt.AuthResponse;

public interface IRestAuthController {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);
}
