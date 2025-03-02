package com.aliacar.service;

import com.aliacar.dto.DtoEmployee;

public interface IEmployeeService {

    DtoEmployee findEmployeeById(Long id);
}
