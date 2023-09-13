package com.example.demo.Employee;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository){
        return args ->{

            Employee John = new Employee(
                    "John",
                    "john@123gmail.com",
                    LocalDate.of(
                            2002,
                            Month.MAY,
                            1

                    )
            );
            Employee Ameli = new Employee(
                    "Ameli",
                    "ameli@123gmail.com",
                    LocalDate.of(
                            1998,
                            Month.FEBRUARY,
                            24

                    )
            );

            repository.saveAll(
                    List.of(John,Ameli)
            );


        };
    }

}
