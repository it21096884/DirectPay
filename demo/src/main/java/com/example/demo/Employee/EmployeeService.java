package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();


    }

    public void addNewEmployee(Employee employee) {
       Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeOptional.isPresent()) {
            throw new IllegalStateException("email already exist");
        }employeeRepository.save(employee);




    }

    public void removeEmployee(Long id) {
        employeeRepository.findById(id);
        boolean exist = employeeRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Student with id "+id+" does not exist");
        }
            employeeRepository.deleteById(id);



    }

    public void updateEmployee(Long id,String name,String email) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(
                        "Employee with Id "+id+" does not exist"
                ));

        if(name != null &&
             name.length() > 0 &&
             !Objects.equals(employee.getName(),name)){
            employee.setName(name);

        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(employee.getEmail(),email)){
            employee.setEmail(email);

        }
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
        if(employeeOptional.isPresent()) {
            throw new IllegalStateException("email is taken");
        }
        employee.setEmail(email);
        }
    }

