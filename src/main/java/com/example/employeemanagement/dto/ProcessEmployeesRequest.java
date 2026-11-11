package com.example.employeemanagement.dto;

import java.util.List;

public class ProcessEmployeesRequest {

    private List<Long> employeeIds;
    private List<Long> departmentIds;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }
}
