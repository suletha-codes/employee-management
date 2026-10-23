package com.example.employeemanagement.controller;


import com.example.employeemanagement.entity.EmployeeProfile;
import com.example.employeemanagement.service.EmployeeProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-profiles")
public class EmployeeProfileController {
    private EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }
    @PostMapping
    public EmployeeProfile createProfile(@RequestBody EmployeeProfile employeeProfile, @RequestParam Long employeeId){
        return employeeProfileService.createProfile(employeeProfile, employeeId);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeProfile getProfileByEmployeeId(@PathVariable Long employeeId){
        return employeeProfileService.getProfileByEmployeeId(employeeId);
    }
}
