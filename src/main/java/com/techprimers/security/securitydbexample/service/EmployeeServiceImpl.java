package com.techprimers.security.securitydbexample.service;

import com.techprimers.security.securitydbexample.interfaces.EmployeeService;
import com.techprimers.security.securitydbexample.utils.DocumentCreator;
import com.techprimers.security.securitydbexample.model.Employee;
import com.techprimers.security.securitydbexample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll()
    {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createNewEmployee(Employee employee)
    {

        return employeeRepository.createNewEmployee(employee.getEmployeeId(), DocumentCreator.createEmployeeDocument(employee));
    }

    @Override
    public void removeEmployee(Employee employee)
    {
        employeeRepository.removeEmployeeById(employee.getEmployeeId());
    }
}
