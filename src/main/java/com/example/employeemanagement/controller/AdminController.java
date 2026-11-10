package com.example.employeemanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "Welcome to admin dashboard";
    }
}
