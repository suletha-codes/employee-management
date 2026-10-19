package com.example.employeemanagement.service;


import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeDepartmentView;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee saveEmployee(Employee employee, Long departmentId){
        Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found with Id "+ departmentId));
        employee.setDepartment(department);
        return employeeRepository.save(employee);

    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //JPQL Joins
    public List<EmployeeDepartmentView> getEmployeeWithDepartment(){
        return employeeRepository.findEmployeeWithDepartment();
    }
}
