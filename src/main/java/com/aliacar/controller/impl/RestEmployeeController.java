package com.aliacar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliacar.dto.DtoEmployee;
import com.aliacar.service.IEmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/employee")
public class RestEmployeeController implements IResEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@Valid @NotEmpty @PathVariable(name = "id")Long id) {
        return employeeService.findEmployeeById(id);
        
    }
}
