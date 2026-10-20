package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e.name, d.name FROM Employee e JOIN e.department d")
    List<EmployeeDepartmentView> findEmployeeWithDepartment();

    List<Employee> findByDepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :depId")
    List<Employee> findEmployeesByDepartmentId(@Param("depId") Long departmentId);
}
