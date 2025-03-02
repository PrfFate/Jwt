package com.aliacar.controller;

import com.aliacar.dto.DtoEmployee;
import com.aliacar.dto.DtoUser;
import com.aliacar.jwt.AuthRequest;

public interface IRestAuthController {

    public DtoUser register(AuthRequest request);

}
