package com.example.employeemanagement.service;


import com.example.employeemanagement.dto.EmployeeWithProfileRequest;
import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeProfile;
import com.example.employeemanagement.entity.Project;
import com.example.employeemanagement.exception.ResourceNotFoundException;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.repository.EmployeeDepartmentView;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;
    private EmployeeWithProfileRequest request;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    public Employee saveEmployee(Employee employee, Long departmentId){
        if(departmentId != null){
            Department department = departmentRepository.findById(departmentId).orElseThrow(()->new ResourceNotFoundException("Department not found with Id "+ departmentId));
            employee.setDepartment(department);
        }else {
            employee.setDepartment(null);
        }

        return employeeRepository.save(employee);

    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //Find by dep_ID
    public List<Employee> getEmployeesByDepartmentId(Long departmentId){
        return employeeRepository.findByDepartmentId(departmentId);
    }
    //Find By Id JPQL
    public List<Employee> getEmployeesByDepartmentIdJPQL(Long departmentId){
        return employeeRepository.findEmployeesByDepartmentId(departmentId);
    }


    //JPQL Joins
    public List<EmployeeDepartmentView> getEmployeeWithDepartment(){
        return employeeRepository.findEmployeeWithDepartment();
    }


    //JPQL Inner Joins
    public List<EmployeeDepartmentView> getEmployeeWithDepartmentIJ(){
        return employeeRepository.findEmployeeWithDepartmentIJ();
    }


    //JPQL Left Joins
    public List<EmployeeDepartmentView> getEmployeeWithDepartmentLJ(){
        return employeeRepository.findEmployeeWithDepartmentLJ();
    }

    //join fetch
    public List<Employee> getAllWithDepartment(){
        return employeeRepository.findAllWithDepartment();
    }

    //ManyToMany ---assignment logic

    public Employee assignProjectToEmployee(Long employeeId, Long projectId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found "+employeeId));
        Project project = projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("Project not found "+ projectId));

        if (!employee.getProjects().contains(project)) {
            employee.getProjects().add(project);
        }
        project.getEmployees().add(employee);
        return employeeRepository.save(employee);
    }

    //CaseCade operations

    public Employee createEmployeeWithProfile(EmployeeWithProfileRequest request){
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());

        EmployeeProfile profile = new EmployeeProfile();
        profile.setAddress(request.getAddress());
        profile.setPhoneNo(request.getPhoneNo());
        profile.setDateOfBirth(request.getDateOfBirth());

        employee.setProfile(profile);
        profile.setEmployee(employee);

        return employeeRepository.save(employee);
    }

    //Merge
    public Employee updateEmployeeWithProfilee(Long employeeId, EmployeeWithProfileRequest request){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found with employee Id "+ employeeId));

        employee.setName(request.getName());
        employee.setSalary(request.getSalary());

        EmployeeProfile profile = employee.getProfile();

        if(profile == null){
            profile = new EmployeeProfile();
            profile.setEmployee(employee);
            employee.setProfile(profile);
        }

        profile.setAddress(request.getAddress());
        profile.setPhoneNo(request.getPhoneNo());
        profile.setDateOfBirth(request.getDateOfBirth());

        return employeeRepository.save(employee);
    }

    //Remove

    public void deleteEmployee(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not nound with eomployee Id "+ employeeId));
        employeeRepository.delete(employee);

    }

    //OrphanRemoval
    public Employee removeEmployeeWithProfile(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found with employee Id "+ employeeId));
        employee.setProfile(null);
        return employeeRepository.save(employee);
    }




}
