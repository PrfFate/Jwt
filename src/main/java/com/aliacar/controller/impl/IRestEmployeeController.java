package com.aliacar.controller.impl;

import com.aliacar.dto.DtoEmployee;

public interface IRestEmployeeController {

    public DtoEmployee findEmployeeById(Long id);
}
