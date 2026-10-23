package com.example.employeemanagement.controller;


import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeDepartmentView;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee, @RequestParam(required = false) Long departmentId){
        return employeeService.saveEmployee(employee, departmentId);

    }

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(required = false) Long departmentId){

        if(departmentId != null){
            return employeeService.getEmployeesByDepartmentId(departmentId);
        }

        return employeeService.getAllEmployees();
    }

    @GetMapping("/by-dept-jpql")
    public List<Employee> getEmployeesByDepartmentIdJPQL(@RequestParam Long departmentId){
        return employeeService.getEmployeesByDepartmentIdJPQL(departmentId);
    }

    @GetMapping("/with-department")
    public List<EmployeeDepartmentView> getEmployeeWithDepartment(){
        return employeeService.getEmployeeWithDepartment();
    }

    @GetMapping("/with-department-ij")
    public List<EmployeeDepartmentView> getEmployeeWithDepartmentIJ(){
        return employeeService.getEmployeeWithDepartmentIJ();
    }

    @GetMapping("/with-department-lj")
    public List<EmployeeDepartmentView> getEmployeeWithDepartmentLJ(){
        return employeeService.getEmployeeWithDepartmentLJ();
    }

    @GetMapping("/fetch-with-department")
    public List<Employee> getAllWithDepartment(){
        return employeeService.getAllWithDepartment();
    }

}
