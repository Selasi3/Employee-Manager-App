package com.example.employeemanager.controller;

import com.example.employeemanager.models.Employee;
import com.example.employeemanager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        return new ResponseEntity(employee, HttpStatus.OK);

    }


    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        Optional<Employee> employeeData = employeeService.findEmployeeById(id);
        if(employeeData.isPresent()){
            Employee _employee = employeeData.get();
            _employee.setEmail(employee.getEmail());
            _employee.setName(employee.getName());
            _employee.setJobTitle(employee.getJobTitle());
            _employee.setPhone(employee.getPhone());

            return new ResponseEntity<>(employeeService.addEmployee(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
       employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
