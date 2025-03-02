package com.aliacar.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliacar.dto.DtoDepartment;
import com.aliacar.dto.DtoEmployee;
import com.aliacar.model.Department;
import com.aliacar.model.Employee;
import com.aliacar.repository.EmployeeRepository;
import com.aliacar.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DtoEmployee findEmployeeById(Long id) {
        DtoEmployee dtoEmployee=new DtoEmployee();
        DtoDepartment dtoDepartment=new DtoDepartment();
        Optional<Employee> optional=employeeRepository.findById(id);
        if(optional.isEmpty()){
            //Exception
            return null;
        }
        Employee employee=optional.get();
        Department department=employee.getDepartment();

        BeanUtils.copyProperties(employee, dtoEmployee);
        BeanUtils.copyProperties(department, dtoDepartment);

        dtoEmployee.setDepartment(dtoDepartment);




        return dtoEmployee;
    }


    
}
