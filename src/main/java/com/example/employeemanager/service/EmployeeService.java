package com.example.employeemanager.service;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee){
      return  employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(Long id){
       return employeeRepository.findEmployeeById(id);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }


    }


