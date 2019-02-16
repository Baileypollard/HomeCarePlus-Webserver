package com.techprimers.security.securitydbexample.interfaces;

import com.techprimers.security.securitydbexample.model.Employee;

import java.util.List;

public interface EmployeeService
{
    List<Employee> findAll();

    Employee createNewEmployee(Employee employee);

    void removeEmployeeById(String id);
}
