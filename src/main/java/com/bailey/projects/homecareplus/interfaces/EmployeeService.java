package com.bailey.projects.homecareplus.interfaces;

import com.bailey.projects.homecareplus.model.Employee;

import java.util.List;

public interface EmployeeService
{
    List<Employee> findAll();

    Employee createNewEmployee(Employee employee);

    void removeEmployee(Employee employee);

    void save(Employee employee);
}
