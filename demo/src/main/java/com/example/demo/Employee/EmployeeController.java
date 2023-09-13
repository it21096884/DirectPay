package com.example.demo.Employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/employeeData")
public class EmployeeController {
    private final EmployeeService employeeservice;

    @Autowired
    public EmployeeController(EmployeeService employeeservice) {
        this.employeeservice = employeeservice;
    }

    @GetMapping(path = "/getEmployee")
    public List<Employee> getEmployee() {
        return employeeservice.getEmployee();
    }


    @PostMapping(path = "/addEmployee")
    public ResponseEntity<String> registerNewEmployee(@RequestBody Employee employee) {
        employeeservice.addNewEmployee(employee);
        return ResponseEntity.ok("Employee added successfully");
    }
    @DeleteMapping(path = "/removeEmployee/{employeeID}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeID") Long id){
        employeeservice.removeEmployee(id);
        return ResponseEntity.ok("Employee removed successfully");
    }
    @PutMapping(path = "/updateEmployee/{employeeID}")
    public ResponseEntity<String> updateEmployee(
        @PathVariable("employeeID") Long employeeID,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email) {
        employeeservice.updateEmployee(employeeID, name, email);
        return ResponseEntity.ok("Employee Updated successfully");
    }

}