package com.aliacar.service;

import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;

public interface IAuthService {

    public DtoUser register(AuthRequest request);

}
