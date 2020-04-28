package com.bailey.projects.homecareplus.service;

import com.bailey.projects.homecareplus.interfaces.EmployeeService;
import com.bailey.projects.homecareplus.repository.EmployeeRepository;
import com.bailey.projects.homecareplus.utils.DocumentCreator;
import com.bailey.projects.homecareplus.model.Employee;
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

    @Override
    public void save(Employee employee)
    {
        employeeRepository.save(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
                employee.getAddress(), employee.getGender(), employee.getPhoneNumber());
    }
}
