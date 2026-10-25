package com.example.employeemanagement.controller;


import com.example.employeemanagement.dto.EmployeeWithProfileRequest;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeDepartmentView;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.http.ResponseEntity;
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
    //ManyToMany

    @PostMapping("/{employeeId}/projects/{projectId}")
    public Employee assignProjectToEmployee(@PathVariable Long employeeId, @PathVariable Long projectId){
        return employeeService.assignProjectToEmployee(employeeId,projectId);
    }
    //OneToOne-CaseCade

    @PostMapping("/with-profile")
    public Employee createEmployeeWithProfile(@RequestBody EmployeeWithProfileRequest request){
        return employeeService.createEmployeeWithProfile(request);
    }
    @PutMapping("/{employeeId}/with-profile")
    public Employee updateEmployeeWithProfile(@PathVariable Long employeeId, @RequestBody EmployeeWithProfileRequest request){
    return employeeService.updateEmployeeWithProfilee(employeeId, request);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

}
