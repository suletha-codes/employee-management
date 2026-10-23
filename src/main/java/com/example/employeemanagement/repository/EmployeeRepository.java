package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :depId")
    List<Employee> findEmployeesByDepartmentId(@Param("depId") Long departmentId);

    //Join
    @Query("SELECT e.name AS employeeName, d.name AS departmentName FROM Employee e JOIN e.department d")
    List<EmployeeDepartmentView> findEmployeeWithDepartment();

    @Query("SELECT e.name AS employeeName, d.name AS departmentName FROM Employee e INNER JOIN e.department d")
    List<EmployeeDepartmentView> findEmployeeWithDepartmentIJ();

    @Query("SELECT e.name AS employeeName, d.name AS departmentName FROM Employee e LEFT JOIN e.department d")
    List<EmployeeDepartmentView> findEmployeeWithDepartmentLJ();

    //Join Fetch
    @Query("SELECT e FROM Employee e JOIN FETCH e.department")
    List<Employee> findAllWithDepartment();
}
