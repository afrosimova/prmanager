package com.afrosimova.prmanager.services;

import com.afrosimova.prmanager.entities.Employee;
import com.afrosimova.prmanager.entities.EmployeeSurvey;
import com.afrosimova.prmanager.repositories.EmployeeRepository;
import com.afrosimova.prmanager.repositories.EmployeeSurveyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findEmployee(long positionId) {
        return employeeRepository.findEmployee(positionId);
    }

}
