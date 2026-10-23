package com.example.employeemanagement.service;


import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeProfile;
import com.example.employeemanagement.repository.EmployeeProfileRepository;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProfileService {
    private EmployeeProfileRepository employeeProfileRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeProfileService(EmployeeProfileRepository profileRepository, EmployeeRepository employeeRepository) {
        this.employeeProfileRepository = profileRepository;
        this.employeeRepository = employeeRepository;
    }
    public EmployeeProfile createProfile(EmployeeProfile employeeProfile, Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new RuntimeException("Employee not found" + employeeId));
        employeeProfile.setEmployee(employee);
        employee.setProfile(employeeProfile);
        return employeeProfileRepository.save(employeeProfile);
    }

    public EmployeeProfile getProfileByEmployeeId(Long employeeId){
        return employeeProfileRepository.findByEmployeeId(employeeId).orElseThrow(()->new RuntimeException("Profile not found for the given employee_Id "+ employeeId));
    }
}
