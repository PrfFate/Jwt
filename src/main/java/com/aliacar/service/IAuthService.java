package com.aliacar.service;

import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;
import com.aliacar.jwt.AuthResponse;

public interface IAuthService {

    public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);
}
