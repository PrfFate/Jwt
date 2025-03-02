package com.aliacar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliacar.dto.DtoEmployee;
import com.aliacar.service.IEmployeeService;



@RestController
@RequestMapping("/employee")
public class RestEmployeeController implements IRestEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/{id}")
    @Override
    public DtoEmployee findEmployeeById(@PathVariable(name = "id")Long id) {
        return employeeService.findEmployeeById(id);
        
    }
}
